package org.hum.pumpkin.transport.impl.netty;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.hum.pumpkin.transport.message.MessageBack;

public class MessageCallback {

	private Lock lock = new ReentrantLock();
	private Condition isFinish = lock.newCondition();
	private MessageBack messageBack;

	public MessageCallback() {
	}
	
	public void finish(MessageBack messageBack) {
		try {
			lock.lock();
			this.messageBack = messageBack;
			this.isFinish.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public MessageBack get() throws InterruptedException {
		try {
			lock.lock();
			this.isFinish.await();
			return messageBack;
		} finally {
			lock.unlock();
		}
	}
}
