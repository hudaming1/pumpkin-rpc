package org.hum.pumpkin.threadpool;

import java.util.concurrent.ExecutorService;

import org.hum.pumpkin.common.serviceloader.support.SPI;

@SPI("jdk")
public interface ThreadPoolFactory {
	ExecutorService create();
}
