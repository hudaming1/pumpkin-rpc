package org.hum.pumpkin_version1.threadpool;

import java.util.concurrent.ExecutorService;

public interface ThreadPoolFactory {
	ExecutorService create();
}
