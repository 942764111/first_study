package cn.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class WebSocketServer {
	
	public static final int PORT = 1984;

	public static void start() throws IOException {

		WebSocketIoHandler handler = new WebSocketIoHandler();

		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );   
		
		acceptor.setHandler(handler);

		acceptor.bind(new InetSocketAddress(PORT));
		System.out.println("启动服务器，端口是：" + PORT);
	}
	

}
