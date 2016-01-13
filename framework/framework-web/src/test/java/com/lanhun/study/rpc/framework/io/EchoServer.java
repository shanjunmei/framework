/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.study.rpc.framework.io;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.net.ServerSocketFactory;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月26日 下午4:52:58
 * @version
 */
public class EchoServer {

    final static Queue<Handler> queue = new ConcurrentLinkedQueue<Handler>();

    public static void main(String[] args) throws Exception {
	ServerSocket listener = ServerSocketFactory.getDefault().createServerSocket(1234);

	Socket socket = listener.accept();

	Handler handler = new Handler(socket);
	queue.add(handler);

    }

    static class ReadWorker implements Runnable {

	Queue<Handler> queue = new ConcurrentLinkedQueue<Handler>();

	@Override
	public void run() {
	    while (true) {
		try {

		} catch (Exception e) {
		    e.printStackTrace();
		}

	    }
	}

    }
    
    static class WriteWorker implements Runnable {

   	Queue<Handler> queue = new ConcurrentLinkedQueue<Handler>();

   	@Override
   	public void run() {
   	    while (true) {
   		try {

   		} catch (Exception e) {
   		    e.printStackTrace();
   		}

   	    }
   	}

       }

    static class MasterWorker implements Runnable {


	@Override
	public void run() {
	    while (true) {
		try {
		    Handler handler=queue.poll();
		    if(handler!=null){
			if(handler.receveQueue.size()>0){
			    //read
			}
			if(handler.sendQueue.size()>0){
			    //write
			}
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

	    }
	}

    }
}
