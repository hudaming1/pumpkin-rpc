package org.hum.pumpkin.exchange;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {

	private static final long serialVersionUID = -3873786891041538794L;
	private long id;
	private String url;
	private int port;
	private Object data;

	/**
	 * TODO 下面的参数会封装到Message的Body中，进行网络传输，因此为了保证传输效率，不应该直接用字段，而是用Map表示，
	 * 这样没用用上的字段也不会出现在传输包中
	 */
	private Map<String, String> params = new HashMap<String, String>();

	@Deprecated
	public Request() {
		// 空构造方法：不推荐使用，这个是为Kryo序列化准备的
	}

	public Request(String url, int port, Object data) {
		id = System.currentTimeMillis();
		this.url = url;
		this.port = port;
		this.data = data;
	}

	public long getId() {
		return this.id;
	}

	public String getUrl() {
		return url;
	}

	public int getPort() {
		return port;
	}

	public Object getData() {
		return data;
	}
	
	public Request buildParam(String key, String value) {
		this.params.put(key, value);
		return this;
	}

	public Map<String, String> getParams() {
		return Collections.unmodifiableMap(params);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Request [url=").append(url).append(", port=").append(port).append(", data=").append(data).append("]");
		return builder.toString();
	}
}
