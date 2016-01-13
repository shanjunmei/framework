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

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.fasterxml.jackson.databind.ObjectMapper;

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
public class AioServer {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private final static Map<String, RealRequest> classCache = new HashMap<>();

    public static void main(String[] args) throws Exception {
	AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open();
	SocketAddress local = new InetSocketAddress(1234);
	listener.bind(local);

	while (true) {

	    Future<AsynchronousSocketChannel> future = listener.accept();

	    AsynchronousSocketChannel channel = future.get();
	    long t = System.currentTimeMillis();

	    String n = MsgHandler.read(channel);

	    Request request = objectMapper.readValue(n, Request.class);

	    String service = request.getTarget();
	    String methodMapping = request.getMethod();

	    String key = request.getTarget() + "." + request.getMethod();
	    RealRequest rr = classCache.get(key);
	    if (rr == null) {
		rr = new RealRequest();
		Class<?> target = Class.forName(service);
		Object to = target.newInstance();
		rr.setTarget(to);

		Method[] methods = target.getDeclaredMethods();
		for (Method method : methods) {
		    if (methodMapping.equals(method.getName())) {
			rr.setMethod(method);

			break;
		    }
		}

		classCache.put(key, rr);
	    }

	    Object ret = rr.getMethod().invoke(rr.getTarget(), request.getParam());

	    MsgHandler.send(channel, ret.toString());

	    t = System.currentTimeMillis() - t;

	    System.out.println("take :" + t + " ms");

	}

    }

}
