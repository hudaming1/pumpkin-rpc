package org.hum.pumpkin.transport.impl.netty.plugins;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.hum.pumpkin.transport.message.Header;
import org.hum.pumpkin.transport.message.Message;
import org.hum.pumpkin.transport.message.MessageBack;
import org.hum.pumpkin.transport.message.MessageTypeEnum;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatServerHandler extends SimpleChannelInboundHandler<Message> {

	private final int _5_SECOND = 5000;
	private final int _10_SECOND = 10000;
	private final Map<String, Long> authedHostMap = new ConcurrentHashMap<>();
	private Timer timer = new Timer(true);
	private AuthController authController;
	
	public HeartBeatServerHandler(AuthController authController) {
		this.authController = authController;
	}

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				for (Entry<String, Long> authHost : authedHostMap.entrySet()) {
					if (System.currentTimeMillis() - authHost.getValue() > _10_SECOND) {
						authedHostMap.remove(authHost.getKey());
					}
				}
			}
		}, _5_SECOND);
        ctx.fireChannelRegistered();
    }
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
		String host = ctx.channel().remoteAddress().toString();
		if (message.getHeader().getType() == MessageTypeEnum.Handshack.getCode()) {
			if (authController.check(host, message.getBody() == null? null : message.getBody().toString())) {
				authedHostMap.put(host, System.currentTimeMillis());
				ctx.writeAndFlush(new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Handshack.getCode()), true));
			} else {
				ctx.writeAndFlush(new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Handshack.getCode()), false));
			}
			return;
		} 
		if (!authedHostMap.containsKey(host)) {
			ctx.writeAndFlush(new MessageBack(new Header(message.getHeader().getMessageId(), MessageTypeEnum.Fail.getCode()), "no auth to access!"));
			return ;
		}
		if (message.getHeader().getType() == MessageTypeEnum.Heartbeat.getCode()) {
			System.out.println("receive host[" + host + "] heartbeat!");
			return ;
		} else {
			ctx.fireChannelRead(message);
		}
	}
}
