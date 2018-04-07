package org.hum.pumpkin.common.serviceloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.common.serviceloader.support.Adaptive;
import org.hum.pumpkin.common.serviceloader.support.MetaData;
import org.hum.pumpkin.common.serviceloader.support.SPI;
import org.hum.pumpkin.common.url.URL;
import org.hum.pumpkin.logger.Logger;
import org.hum.pumpkin.logger.LoggerFactory;
import org.hum.pumpkin.util.StringUtils;

/**
 * 扩展加载器
 * <pre>
 * 	v1.0
 * 	  1.实现Map配置properties
 *    2.实现有参数构造方法
 * 	  3.实现自动set方法
 * </pre>
 * <pre>
 * 	v2.0
 * 	  1.实现Activite标签，构造invokerChain和FilterChain
 * 	  2.实现Adapter标签，实现URL自适应加载机制（其实很类似于spring中xml配置bean.ref来解耦实现，只不过dubbo中是在运行时动态改变改变）。	
 * </pre>
 */
public class ExtensionLoader<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExtensionLoader.class);
	// FileMap -> FileName, Key, ClassName
	private static final MulitHashMap<String, String, String> FileMap = new MulitHashMap<>();
	// LoadMap
	private static final Map<Class<?>, ExtensionLoader<?>> extensionLoaderMap = new ConcurrentHashMap<>();
	// 
	private static final String PUMPKIN_SERVICE_PATH = "META-INF/services/";
	// 
	private static final String PUMPKIN_CONFIG_PATH = "META-INF/pumpkin/";
	
	static {
		try {
			parseFile(PUMPKIN_SERVICE_PATH);
			parseFile(PUMPKIN_CONFIG_PATH);
		} catch (Exception ce) {
			throw new PumpkinException("parse file exception", ce);
		}
	}
	private static void parseFile(String directory) throws IOException {
		Enumeration<java.net.URL> urls = getClassLoader().getResources(directory);
		while (urls.hasMoreElements()) {
			File directoryInst = new File(urls.nextElement().getFile());
			for (File file : directoryInst.listFiles()) {
				FileMap.append(file.getName(), parseProperties(file));
				LOGGER.debug("load extension file [" + file.getName() + "]");
			}
		}
	}
	
	private static Map<String, String> parseProperties(File file) throws FileNotFoundException, IOException {
		Map<String, String> content = new HashMap<>();
		Properties props = new Properties();
		props.load(new FileInputStream(file));
		for (Entry<Object, Object> entry : props.entrySet()) {
			content.put(entry.getKey().toString(), entry.getValue().toString());
			LOGGER.debug("load extension [" + file.getName() + "] properties: {" + entry.getKey().toString() + ":" + entry.getValue().toString() + "}");
		}
		return content;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> classType) {
		ExtensionLoader<T> extensionLoader = (ExtensionLoader<T>) extensionLoaderMap.get(classType);
		if (extensionLoader == null) {
			extensionLoader = new ExtensionLoader<>(classType);
			extensionLoaderMap.putIfAbsent(classType, extensionLoader);
		}	
		return extensionLoader;
	}
	
	/**
	 * XXX 参照java.util.ServiceLoader第345行，应该取分classloader加载。
	 * 	我这种写法应该是无法加载ExtClassLoader和RootClassLoader下的文件
	 * <pre>
	 *  取分boot、ext、app这3个ClassLoader，我的写法只能加载System.getProperty("java.class.path")目录下的properties。
	 *	 boot:	System.out.println(System.getProperty("sun.boot.class.path"));
	 *	 ext:	System.out.println(System.getProperty("java.ext.dirs"));
	 *	 app:	System.out.println(System.getProperty("java.class.path"));
	 * </pre>
	 * @return
	 */
	private static ClassLoader getClassLoader() {
		return ExtensionLoader.class.getClassLoader();
	}
	
	private Class<T> interfaceType;
	private MetaData metaData;
	private Object adaptLock = new Object();
	private final Map<String, T> instanceMap = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, Object> CREATE_EXTENSION_LOCK = new ConcurrentHashMap<>();
	private volatile T adaptInstance;
	private volatile T defaultInstance;
	
	private ExtensionLoader(Class<T> interfaceType) {
		this.interfaceType = interfaceType;
		// 解析classType上的注解成MetaData
		this.metaData = new MetaData(interfaceType);
	}

	public T get(String extensionName) {
		T instance = instanceMap.get(extensionName);
		if (instance == null) {
			instance = createInstance(extensionName);
			instanceMap.putIfAbsent(extensionName, instance);
		}
		return instance;
	}
	
	/**
	 * 获得默认Extension
	 * <pre>
	 * 	1.如果只有一个extension，则直接返回
	 * 	2.如果有多个，则根据defaultName取出
	 * 	3.如果没有，则抛出异常
	 * </pre>
	 * <pre>
	 * 	get和getAdaptive虽然都返回T，但get返回的是T实例；getAdaptive返回的是代理对象。
	 * </pre>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getDefault() {
		if (defaultInstance != null) {
			return defaultInstance;
		}
		if (instanceMap.size() == 1) {
			defaultInstance = (T) instanceMap.values().toArray()[0];
			return defaultInstance;
		}
		defaultInstance = get(metaData.getDefaultExtName());
		if (defaultInstance == null) {
			throw new PumpkinException("no default extension for classType[" + interfaceType.getName() + "]");
		}
		return defaultInstance;
	}
	
	private T createInstance(String extensionName) {
		try {
			if (instanceMap.get(extensionName) != null) {
				return instanceMap.get(extensionName);
			}
			synchronized (getCreateExtensionLock(extensionName)) {
				if (instanceMap.get(extensionName) == null) {
					String className = FileMap.get(interfaceType.getName(), extensionName);
					if (className == null || className.trim().length() == 0) {
						throw new PumpkinException("cann't find className for [" + interfaceType.getName() + "] with key [" + extensionName + "]");
					}
					instanceMap.put(extensionName, createInstances(className));
				}
				return instanceMap.get(extensionName);
			}
		} catch (PumpkinException e) {
			throw e;
		} catch (Exception e) {
			throw new PumpkinException("instance class type [" + interfaceType.getName() + "] failed", e);
		}
	}
	
	public Collection<T> getExtensionList() {
		return Collections.unmodifiableCollection(instanceMap.values());
	}
	
	/**
	 * 返回代理对象，自动适配加载
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getAdaptive() {
		if (adaptInstance == null) {
			synchronized (adaptLock) {
				if (adaptInstance == null) {
					adaptInstance = (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[] { interfaceType }, new AdaptiveProxy());
				}
			}
		}
		return adaptInstance;
	}
	
	// 初始化extension，将File中的配置实例化成对象
	private void preloadExtensions() {
		// 1.根据配置文件读出ImplementClass
		Map<String, String> map = FileMap.get(interfaceType.getName());
		// 2.实例化Class对象
		for (Entry<String, String> entry : map.entrySet()) {
			if (instanceMap.containsKey(entry.getKey())) {
				continue;
			}
			synchronized (getCreateExtensionLock(entry.getKey())) {
				if (instanceMap.get(entry.getKey()) == null) {
					T instance = createInstances(entry.getValue());
					instanceMap.putIfAbsent(entry.getKey(), instance);
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private T createInstances(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			Object instance = clazz.newInstance();
			// TODO 自动注入setter方法
			return (T) instance;
		} catch (Exception ce) {
			throw new PumpkinException("instance classType[" + className + "] occured exception", ce);
		}
	}

	private Object getCreateExtensionLock(String key) {
		Object lock = CREATE_EXTENSION_LOCK.get(key);
		if (lock == null) {
			CREATE_EXTENSION_LOCK.putIfAbsent(key, new Object());
		}
		return CREATE_EXTENSION_LOCK.get(key);
	}

	/**
	 * 自动适配规则
	 * <pre>
	 *   dubbo适配规则：
	 *   	1.如果参数url.key对应的Extension存在，则优先使用
	 *   	2.如果url.key无对应的Extension，则使用Class的SPI.value作为默认值
	 *   	3.如果SPI.value也没有对应Extension，则抛出异常
	 * </pre>
	 * <pre>
	 * 	 pumpkin适配规则：
	 * 		1.如果只有一个Extension被加载，则不用判断后续key，直接适配
	 * 		2.如果有多个Extension被加载，则优先使用url.key对应的Extension
	 * 		3.如果url.key无对应的Extension，则使用SPI.value
	 * 		4.如果SPI.value业务对应，则提示异常
	 * </pre>
	 * <pre>
	 * 	 Dubbo中这个方法返回的对象是通过JavassistCompiler动态编译而成，but why？性能问题吗？ 
	 * 		Dubbo使用动态编译，考虑的应该不是灵活性设计，而是出于性能问题，因为被adaptive修饰的类或
	 * 	方法可能会频繁被调用（理论上回出现，但目前代码中还没找到，后续我需要再留意以待佐证我的猜测）。
	 * </pre>
	 * @return
	 */
	class AdaptiveProxy implements InvocationHandler {
		
		public AdaptiveProxy() {
			// adapt前需要保证interfaceType下所有的实现类已经实例化
			preloadExtensions();
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
			// 被@Adaptive标注的方法，限定所传至少一个参数是URL类型
			URL url = null;
			for (Object param : params) {
				if (param instanceof URL) {
					url = (URL) param;
					break;
				}
			}
			if (url == null) {
				throw new IllegalArgumentException("params must contains class type [" + URL.class.getName() + "]");
			}
			Object proxyInstance = getAdaptiveExtension(method, url);
			if (proxyInstance == null) {
				throw new PumpkinException("can't find adapt " + interfaceType.getName());
			}
			return method.invoke(proxyInstance, params);
		}
		
		private Object getAdaptiveExtension(Method method, URL url) {
			if (instanceMap.isEmpty()) {
				return null;
			} else if (instanceMap.size() == 1) {
				// 1.如果只有一个Extension被加载，则不用判断后续key，直接适配
				return instanceMap.values().toArray()[0];
			} 
			// 2.如果有多个Extension被加载，则优先使用url.key对应的Extension
			String adaptiveKeyName = getAdaptiveKeyName(method);
			if (adaptiveKeyName != null) {
				Object p = url.getParam(adaptiveKeyName);
				String extensionName = (p == null? "" : p.toString());
				T t = instanceMap.get(extensionName);
				if (t != null) {
					return t;
				}
			}
			// 3.如果url.key无对应的Extension，则使用SPI.value
			String extensionName = getSpiKeyName(method);
			T t = instanceMap.get(extensionName);
			// 4.如果SPI.value业务对应，则提示异常
			if (t == null) {
				throw new PumpkinException("can't adapt extension for " + interfaceType.getName());
			}
			return t;
		}
		
		private String getAdaptiveKeyName(Method method) {
			Adaptive adaptAnno = method.getAnnotation(Adaptive.class);
			if (adaptAnno == null) {
				return null;
			} else if (StringUtils.isNotEmpty(adaptAnno.value())) {
				return adaptAnno.value();
			}
			return null;
		}
		
		private String getSpiKeyName(Method method) {
			SPI spiAnno = method.getDeclaringClass().getAnnotation(SPI.class);
			return spiAnno.value();
		}
	}
	
	static class MulitHashMap<K, V, E> {
		private final Map<K, Map<V, E>> MultiMap = new ConcurrentHashMap<K, Map<V, E>>();
		
		public void put(K k, V v, E e) {
			Map<V, E> map = MultiMap.get(k);
			if (map == null) {
				MultiMap.putIfAbsent(k, new ConcurrentHashMap<V, E>());
				map = MultiMap.get(k);
			}
			if (map.putIfAbsent(v, e) != null) {
				// if 'k.v' exists
				throw new KeyDuplicateExistsException("put value [" + e + "] failed, key [" + k + "." + v + "] has exists");
			}
		}
		
		public void append(K k, Map<V, E> map) {
			Map<V, E> _map = MultiMap.get(k);
			if (_map == null) {
				MultiMap.putIfAbsent(k, new ConcurrentHashMap<V, E>());
				_map = MultiMap.get(k);
			}
			synchronized (_map) {
				_map.putAll(map);
				MultiMap.put(k, _map);
			}
		}
		
		public Map<V, E> get(K k) {
			return MultiMap.get(k);
		}
		
		public E get(K k, V v) {
			if (k == null) {
				throw new NullPointerException("k mustn't be null");
			}
			Map<V, E> map = MultiMap.get(k);
			if (map == null) {
				throw new KeyNotExistsException("k [" + k + "] is not exists!");
			}
			return map.get(v);
		}
		
		static class KeyNotExistsException extends RuntimeException {
			private static final long serialVersionUID = 1L;
			public KeyNotExistsException(String msg) {
				super(msg);
			}
		}

		static class KeyDuplicateExistsException extends RuntimeException {
			private static final long serialVersionUID = 1L;
			public KeyDuplicateExistsException(String msg) {
				super(msg);
			}
		}
	}
	
	public String toString() {
		return "ExtensionLoader[" + interfaceType.getName() + "]";
	}
}