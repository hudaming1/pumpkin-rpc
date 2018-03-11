package org.hum.pumpkin.common.exception;

public class ServiceException extends PumpkinException {

	private static final long serialVersionUID = 7134689591084139708L;

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
