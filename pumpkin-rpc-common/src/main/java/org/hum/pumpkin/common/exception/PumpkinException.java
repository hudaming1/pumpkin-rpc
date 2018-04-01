package org.hum.pumpkin.common.exception;

public class PumpkinException extends RuntimeException {

	private static final long serialVersionUID = 6563545307157303600L;
	
	public PumpkinException(String message) {
		super(message, null);
	}

	public PumpkinException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
