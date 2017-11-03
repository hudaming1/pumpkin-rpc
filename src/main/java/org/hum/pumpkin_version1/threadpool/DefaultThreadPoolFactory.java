package org.hum.pumpkin_version1.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultThreadPoolFactory implements ThreadPoolFactory {

	private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	
	@Override
	public ExecutorService create() {
		return executorService;
	}
}
