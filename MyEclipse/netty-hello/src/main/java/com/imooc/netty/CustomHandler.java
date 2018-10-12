package com.imooc.netty;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http2.Http2DataFrame;
import io.netty.util.CharsetUtil;

/**
 *  创建自定义助手类
 * @author John
 *
 */
//SimpleChannelInboundHandler： 对于请求来说，相当于入栈、入境
public class CustomHandler  extends SimpleChannelInboundHandler<HttpObject>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		//根据上下文获取channel
		Channel channel=ctx.channel();
		//显示客户端的远程地址
		
		if(msg instanceof HttpRequest){
			System.out.println("HttpRequest 请求");
			{

				//定义发送的数据消息
				ByteBuf content=Unpooled.copiedBuffer("Hello netty~", CharsetUtil.UTF_8);
				
				//构建一个http Response
				FullHttpResponse response=
						new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
								HttpResponseStatus.OK,
								content);
				//为相应增加数据类型和长度
				response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
				response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
				
				//把相应刷到客户端
				ctx.writeAndFlush(response);
			}
		}
		
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel......注册");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel......移除");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel......活跃");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel......不活跃");
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel读取数据完毕......");
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("用户事件触发......注册");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channel可写更改......");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("异常捕获......");
		ctx.channel().closeFuture();
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Handler添加......");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Handler移除......");
		super.handlerRemoved(ctx);
	}
	
}
