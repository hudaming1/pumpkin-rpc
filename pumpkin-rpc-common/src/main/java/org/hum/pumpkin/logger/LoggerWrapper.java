package org.hum.pumpkin.logger;

public class LoggerWrapper implements Logger {

	private Logger logger;

	public LoggerWrapper(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void trace(String msg) {
		try {
			logger.trace(msg);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void trace(Throwable e) {
		try {
			logger.trace(e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void trace(String msg, Throwable e) {
		try {
			logger.trace(msg, e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void debug(String msg) {
		try {
			logger.debug(msg);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void debug(Throwable e) {
		try {
			logger.debug(e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void debug(String msg, Throwable e) {
		try {
			logger.debug(msg, e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void info(String msg) {
		try {
			logger.info(msg);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void info(Throwable e) {
		try {
			logger.info(e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void info(String msg, Throwable e) {
		try {
			logger.info(msg, e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void warn(String msg) {
		try {
			logger.warn(msg);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void warn(Throwable e) {
		try {
			logger.warn(e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void warn(String msg, Throwable e) {
		try {
			logger.warn(msg, e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void error(String msg) {
		try {
			logger.error(msg);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void error(Throwable e) {
		try {
			logger.error(e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public void error(String msg, Throwable e) {
		try {
			logger.trace(msg, e);
		} catch (Throwable ignore) {
		}
	}

	@Override
	public boolean isTraceEnabled() {
		try {
			return logger.isTraceEnabled();
		} catch (Throwable ignore) {
			return false;
		}
	}

	@Override
	public boolean isDebugEnabled() {
		try {
			return logger.isDebugEnabled();
		} catch (Throwable ignore) {
			return false;
		}
	}

	@Override
	public boolean isInfoEnabled() {
		try {
			return logger.isInfoEnabled();
		} catch (Throwable ignore) {
			return false;
		}
	}

	@Override
	public boolean isWarnEnabled() {
		try {
			return logger.isWarnEnabled();
		} catch (Throwable ignore) {
			return false;
		}
	}

	@Override
	public boolean isErrorEnabled() {
		try {
			return logger.isErrorEnabled();
		} catch (Throwable ignore) {
			return false;
		}
	}
}
