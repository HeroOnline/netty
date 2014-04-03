package com.yxl.handler;

import java.net.InetSocketAddress;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.yxl.main.ServerMain;

/**
 * Server�������Handler
 * 
 * @author yuanxiaolong.sam
 * 
 * 
 */
public class ServerHandler extends SimpleChannelUpstreamHandler {

	// Server�յ�Client��Ϣ���� 
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Channel channel = e.getChannel();
		if (channel != null) {
			String host = ((InetSocketAddress) ctx.getChannel()
					.getRemoteAddress()).getAddress().getHostAddress();
			int port = ((InetSocketAddress) ctx.getChannel().getRemoteAddress())
					.getPort();
			//��ip��host��װ����,�ŵ�map��,���ڹ�����Client������
			ServerMain.channelMap.put(host + ":" + port, channel);
		}
		System.out.println("Server recive message: " + e.getMessage());
		//�鿴��ǰ����Map
		System.out.println(ServerMain.channelMap);
	}

	// �쳣����ص�
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		System.err.println(" Server has a error,Error cause:" + e.getCause());
		e.getChannel().close();
	}

	// ���ӹرմ���
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
		String host = ((InetSocketAddress) ctx.getChannel()
				.getRemoteAddress()).getAddress().getHostAddress();
		int port = ((InetSocketAddress) ctx.getChannel().getRemoteAddress())
				.getPort();
		System.out.println("server close channel: " + "[" + host + ":" + port + "]");
		//�Ƴ���������
		try {
			ServerMain.channelMap.remove(host + ":" + port);
		} catch (Exception e2) {
			//�������Ϊ�˸��߿���,���Խ�ɾ��ʧ�ܵļ���һ���������̨����һ��cleanup���߳�
		}
	}

}
