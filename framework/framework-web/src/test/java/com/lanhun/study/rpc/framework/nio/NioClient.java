/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.study.rpc.framework.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SocketChannel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanhun.study.rpc.framework.nio.NioServer.Worker;
import com.lanhun.study.rpc.test.HelloServiceImpl;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月26日 上午11:24:00
 * @version
 */
public class NioClient {

    public static void main(String[] args) throws Exception {
	SocketChannel channel = SocketChannel.open();
	channel.connect(new InetSocketAddress("127.0.0.1", 1234));

	Worker worker = new Worker(channel);
	Thread t = new Thread(worker);
	t.start();
    }

    static class Worker implements Runnable {

	SocketChannel channel;

	Worker(SocketChannel channel) {
	    this.channel = channel;
	}

	@Override
	public void run() {

	    while (true) {
		ByteBuffer bytebuffer = ByteBuffer.wrap("hello".getBytes());
		// bytebuffer.flip();
		System.out.println(bytebuffer.limit());
		try {
		    channel.write(bytebuffer);
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		bytebuffer.clear();

	    }

	}

    }
}