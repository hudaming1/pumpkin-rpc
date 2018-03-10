package org.hum.pumpkin.transport.impl.netty.plugins;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.hum.pumpkin.transport.message.Header;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.message.MessageTypeEnum;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.ScheduledFuture;

public class HeartBeatClientHandler extends SimpleChannelInboundHandler<MessageBack> {

	private CountDownLatch startLatch = new CountDownLatch(1);
	private volatile ScheduledFuture<?> heartBeat;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Message message = new Message(new Header(System.currentTimeMillis(), MessageTypeEnum.Handshack.getCode()), null);
		ctx.writeAndFlush(message);
		ctx.fireChannelActive();
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageBack messageBack) throws Exception {
		if (messageBack.getHeader().getType() == MessageTypeEnum.Handshack.getCode()) {
			if (messageBack.getBody() == Boolean.TRUE) {
				startLatch.countDown();
				heartBeat = ctx.executor().scheduleWithFixedDelay(new HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
			} else {
				// 如果握手失败，则关闭连接
				ctx.close();
			}
			return;
		} else if (messageBack.getHeader().getType() == MessageTypeEnum.Heartbeat.getCode()) {
			
			return ;
		}
		ctx.fireChannelRead(messageBack);
	}
	
	public void waitHandShake() {
		try {
			startLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static class HeartBeatTask implements Runnable {
		private ChannelHandlerContext ctx;
		public HeartBeatTask(ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}
		
		@Override
		public void run() {
			Message heartBeatMessage = new Message(new Header(System.currentTimeMillis(), MessageTypeEnum.Heartbeat.getCode()), null);
			ctx.writeAndFlush(heartBeatMessage);
			System.out.println("send heartbeat request");
		}
	}

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    		if (heartBeat != null) {
    			heartBeat.cancel(true);
    			heartBeat = null;
    		}
        ctx.fireExceptionCaught(cause);
    }
}
