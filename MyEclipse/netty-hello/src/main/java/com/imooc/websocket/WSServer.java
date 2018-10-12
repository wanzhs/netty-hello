package com.imooc.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WSServer {
	public static void main(String[] args) throws Exception{
		EventLoopGroup mainGroup=new NioEventLoopGroup();
		EventLoopGroup subGroup=new NioEventLoopGroup();
		
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(mainGroup, subGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new WSServerInitializer());
			ChannelFuture future = serverBootstrap.bind(8088).sync();
			future.channel().closeFuture().sync();
		} finally {
			// TODO: handle finally clause
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		}
	}
}
