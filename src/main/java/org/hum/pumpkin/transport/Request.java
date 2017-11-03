package org.hum.pumpkin.transport;

public class Request {

	private String url;
	private int port;
	private Object data;
	private int timeout;
	private int retryTimes;

	public Request(String url, int port, Object data) {
		this.url = url;
		this.port = port;
		this.data = data;
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

	public int getTimeout() {
		return timeout;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Request [url=").append(url).append(", port=").append(port).append(", data=").append(data)
				.append(", timeout=").append(timeout).append(", retryTimes=").append(retryTimes).append("]");
		return builder.toString();
	}
}
