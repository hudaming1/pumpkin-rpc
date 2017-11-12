package org.hum.pumpkin.exchange.server;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.hum.pumpkin.exchange.Request;
import org.hum.pumpkin.exchange.Response;
import org.hum.pumpkin.exporter.Tasker;
import org.hum.pumpkin.invoker.RpcInvocation;
import org.hum.pumpkin.invoker.RpcResult;
import org.hum.pumpkin.transport.ServerHandler;

public class ExchangeServerHandler {
	private ServerHandler serverHandler = new ServerHandler() {
		@Override
		public Response received(final Request request) {
			if (!(request.getData() instanceof RpcInvocation)) {
				// TODO 这里改怎么处理？
				return new Response(request.getId(), null, null);
			}
			Future<RpcResult> future = EXECUTOR_SERVICE.submit(new Tasker((RpcInvocation) request.getData(), ref));
			try {
				RpcResult result = future.get();
				return new Response(request.getId(), result, null);
			} catch (InterruptedException | ExecutionException e) {
				return new Response(request.getId(), null, e);
			}
		}
	};
}
