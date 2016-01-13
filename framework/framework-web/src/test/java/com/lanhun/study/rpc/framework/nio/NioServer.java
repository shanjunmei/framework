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

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月26日 上午11:14:07
 * @version
 */
public class NioServer {

    private static Selector selector;

    public static void main(String[] args) throws Exception {
	ServerSocketChannel listener = ServerSocketChannel.open();
	SocketAddress local = new InetSocketAddress(1234);
	listener.socket().bind(local);
	listener.configureBlocking(false);
	selector = Selector.open();
	listener.register(selector, SelectionKey.OP_ACCEPT);
	while (true) {

	    selector.select();
	    Iterator<SelectionKey> it = selector.selectedKeys().iterator();

	    while (it.hasNext()) {
		SelectionKey key = it.next();
		it.remove();
		if (key.isReadable()) {
		   
		}
		if (key.isWritable()) {

		}
		if (key.isAcceptable()) {

		}
	    }

	}

    }

    static class Worker implements Runnable {

	SocketChannel channel;

	Worker(SocketChannel channel) {
	    this.channel = channel;
	}

	@Override
	public void run() {

	    while (true) {
		try {
		    ByteBuffer bytebuffer = ByteBuffer.allocate(32);
		    channel.read(bytebuffer);
		    bytebuffer.flip();
		    byte[] byes = new byte[bytebuffer.limit()];
		    System.out.println(new String(byes));

		    bytebuffer.clear();
		} catch (Exception e) {
		    e.printStackTrace();
		}

	    }

	}

    }

}
