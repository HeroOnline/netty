package com.yxl.main;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jboss.netty.channel.Channel;

import com.yxl.serverpull.ServerPullUitl;
import com.yxl.thread.ServerThread;

/**
 * ��������������
 * @author yuanxiaolong.sam 
 *
 * 
 */
public class ServerMain {

	//�̰߳�ȫmap,���������holdס�ͻ������ӵ�channel
	public static ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();

	public static void main(String[] args) {
		
		try {
			//����������
			ServerThread r = new ServerThread();
			Thread t = new Thread(r);
			t.setName("server thread");
			t.start();

			//������Ϣ
			ServerPullUitl.pullMsg();
		} catch (Exception e) {
			System.out.println("know exception on server: " + e.getMessage());
		}

	}

}
