/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.lanhun.study.rpc.framework.nio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RpcFramework
 * 
 * @author william.liangf
 */
public class RpcFramework {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private static final class Worker implements Runnable {

	private final Socket socket;

	private Worker(Socket socket) {
	    this.socket = socket;
	}

	@Override
	public void run() {
	    try {
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		while (reader.ready()) {
		    sb.append(reader.readLine());
		}
		System.out.println(sb);

		Map<String, Object> response = new HashMap<>();
		response.put("code", "0");
		response.put("msg", "成功");

		// objectMapper.writeValue(out, response);

		String responseStr = objectMapper.writeValueAsString(response);
		responseStr += "\r\n";
		out.write(responseStr.getBytes());
		out.flush();

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * 暴露服务
     * 
     * @param service 服务实现
     * @param port 服务端口
     * @throws Exception
     */

    private static ExecutorService executor = Executors.newFixedThreadPool(1000);

    public static void export(int port) throws Exception {

	if (port <= 0 || port > 65535)
	    throw new IllegalArgumentException("Invalid port " + port);
	System.out.println("Export service  on port " + port);
	ServerSocket server = new ServerSocket(port);
	for (;;) {
	    try {
		final Socket socket = server.accept();

		// executor.execute(new Worker(socket));

		Thread w = new Thread(new Worker(socket));
		w.start();

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * 引用服务
     * 
     * @param <T> 接口泛型
     * @param interfaceClass 接口类型
     * @param host 服务器主机名
     * @param port 服务器端口
     * @return 远程服务
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {
	if (interfaceClass == null)
	    throw new IllegalArgumentException("Interface class == null");
	if (!interfaceClass.isInterface())
	    throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
	if (host == null || host.length() == 0)
	    throw new IllegalArgumentException("Host == null!");
	if (port <= 0 || port > 65535)
	    throw new IllegalArgumentException("Invalid port " + port);
	System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
	return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass }, new InvocationHandler() {

	    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {

		Socket socket = new Socket(host, port);

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		Map<String, Object> request = new HashMap<>();

		request.put("service", method.getName());
		request.put("param", arguments);

		String requestStr = objectMapper.writeValueAsString(request);
		requestStr += "\r\n";
		out.write(requestStr.getBytes());
		out.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		while (reader.ready()) {
		    sb.append(reader.readLine());
		}
		System.out.println(sb);
		return "";

	    }
	});
    }

    public static void main(String[] args) throws JsonProcessingException {
	Map<String, Object> response = new HashMap<>();
	response.put("code", "0");
	response.put("msg", "成功");
	byte[] x = objectMapper.writeValueAsBytes(response);
	System.out.println(new String(x));
    }

}
