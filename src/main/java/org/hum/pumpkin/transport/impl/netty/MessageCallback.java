package org.hum.pumpkin.transport.impl.netty;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.hum.pumpkin.exchange.Response;

public class MessageCallback {

	private Lock lock = new ReentrantLock();
	private Condition isFinish = lock.newCondition();
	private Response response;

	public MessageCallback() {
	}
	
	public void finish(Response response) {
		try {
			lock.lock();
			this.response = response;
			this.isFinish.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public Response get() throws InterruptedException {
		try {
			lock.lock();
			this.isFinish.await();
			return response;
		} finally {
			lock.unlock();
		}
	}
}
