package org.hum.pumpkin.exchange;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 8783577974211954621L;
	private long requestId;
	private Object data;
	private Throwable error;
	
	@Deprecated
	public Response() {
		// 空构造方法：不推荐使用，这个是为Kryo序列化准备的
	}
	
	public Response(long requestId, Object data, Throwable error) {
		this.requestId = requestId;
		this.data = data;
		this.error = error;
	}
	
	public long getRequestId() {
		return this.requestId;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public Throwable getError() {
		return this.error;
	}
}
