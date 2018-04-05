package org.hum.pumpkin.logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.logger.slf4j.Slf4jLogger;

public class LoggerFactory {
	
	private static final Map<String, LoggerWrapper> LOGGERS = new ConcurrentHashMap<>();
	
	public static Logger getLogger(Class<?> classType) {
		return getLogger(classType.getName());
	}

	public static Logger getLogger(String loggerName) {
		LoggerWrapper loggerWrapper = LOGGERS.get(loggerName);
		if (loggerWrapper == null) {
			LOGGERS.putIfAbsent(loggerName, new LoggerWrapper(new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(loggerName))));
			loggerWrapper = LOGGERS.get(loggerName);
		}
		return loggerWrapper;
	}
}
