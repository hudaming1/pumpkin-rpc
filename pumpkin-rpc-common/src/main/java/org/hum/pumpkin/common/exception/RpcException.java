package org.hum.pumpkin.common.exception;

public class RpcException extends PumpkinException {

	private static final long serialVersionUID = -4852969632068397402L;
	
	public RpcException(String message) {
		this(message, null);
	}
	
	public RpcException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
