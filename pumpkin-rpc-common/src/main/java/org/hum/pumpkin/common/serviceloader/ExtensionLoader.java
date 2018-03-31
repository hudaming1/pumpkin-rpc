package org.hum.pumpkin.common.serviceloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.common.exception.PumpkinException;
import org.hum.pumpkin.common.serviceloader.support.MetaData;

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
 * 	  2.实现Adapter标签，实现URL自适应加载机制（其实很类似于spring中xml配置bean.ref来解耦实现，只不过dubbo中是在运行时动态注入）。	
 * </pre>
 */
public class ExtensionLoader<T> {

	// FileMap -> FileName, Key, ClassName
	private static final MulitHashMap<String, String, String> FileMap = new MulitHashMap<>();
	// InstanceMap -> InterfaceClass, Key, Object
	private static final MulitHashMap<Class<?>, String, Object> InstanceMap = new MulitHashMap<>();
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
			}
		}
	}
	
	private static Map<String, String> parseProperties(File file) throws FileNotFoundException, IOException {
		Map<String, String> content = new HashMap<>();
		Properties props = new Properties();
		props.load(new FileInputStream(file));
		for (Entry<Object, Object> entry : props.entrySet()) {
			content.put(entry.getKey().toString(), entry.getValue().toString());
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
	
	private Class<T> classType;
	private MetaData metaData;
	private final Map<String, T> instanceMap = new ConcurrentHashMap<>();
	
	private ExtensionLoader(Class<T> classType) {
		this.classType = classType;
		// 解析classType上的注解成MetaData
		this.metaData = new MetaData(classType);
	}

	public T get(String extensionName) {
		T instance = instanceMap.get(extensionName);
		if (instance == null) {
			instance = createInstance(extensionName);
			instanceMap.putIfAbsent(extensionName, instance);
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	private T createInstance(String extensionName) {
		try {
			if (InstanceMap.get(classType) == null || InstanceMap.get(classType, extensionName) == null) {
				String className = FileMap.get(classType.getName(), extensionName);
				Class<?> clazz = Class.forName(className);
				Object instance = clazz.newInstance();
				InstanceMap.put(classType, extensionName, instance);
			}
			return (T) InstanceMap.get(classType, extensionName);
		} catch (Exception e) {
			throw new PumpkinException("instance class type [" + classType.getName() + "] failed", e);
		}
	}
	
	public T getDefaultExtension() {
		return get(metaData.getDefaultExtName());
	}
	
	/**
	 * TODO 参照java.util.ServiceLoader第345行，应该取分classloader加载。
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
}





