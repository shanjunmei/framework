/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.study.rpc.framework;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class AioClient {

    final static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
	// for (int i = 0; i < 10; i++) {
	t();
	// }

    }

    private static void t() throws Exception {
	String src = "hello";

	Request request = new Request();
	request.setTarget(HelloServiceImpl.class.getName());
	request.setMethod("hello");
	request.setParam("li miao ");
	src = objectMapper.writeValueAsString(request);

	//for (int i = 0; i < 10; i++) {
	    AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
	    channel.connect(new InetSocketAddress("127.0.0.1", 1234));
	    
	    
	    long t = System.currentTimeMillis();

	    MsgHandler.send(channel, src);

	    String x = MsgHandler.read(channel);
	    System.out.println(x);

	    MsgHandler.send(channel, src);
	    x = MsgHandler.read(channel);
	    
	    System.out.println(x);

	    t = System.currentTimeMillis() - t;
	    System.out.println("invoke :" + request.getTarget() + "." + request.getMethod() + " success ,take : " + t + " ms");
	//}
    }
}