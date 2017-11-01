package org.hum.pumpkin.threadpool;

import java.util.concurrent.ExecutorService;

public interface ThreadPoolFactory {
	ExecutorService create();
}
