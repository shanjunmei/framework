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

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月26日 下午5:26:52
 * @version
 */
public class Handler {

    final Socket socket;

    final Queue<String> sendQueue = new ConcurrentLinkedQueue<String>();

    final Queue<String> receveQueue = new ConcurrentLinkedQueue<String>();

    public Handler(Socket socket) {
	this.socket = socket;
    }

    
    public void write(String content) throws IOException {
	content = appendZero(content.getBytes().length, Constant.HEADER_LENGTH) + content;

	socket.getOutputStream().write(content.getBytes());
	socket.getOutputStream().flush();
    }

    public static String appendZero(int num, int length) {
	// String.valueOf()是用来将其他类型的数据转换为string型数据的
	String tmpString = String.valueOf(num);
	for (int i = tmpString.length(); i < length; i++) {
	    tmpString = "0" + tmpString;
	}
	return tmpString;
    }

    public String read(Socket socket) throws IOException {
	InputStream in = socket.getInputStream();
	byte[] header = new byte[Constant.HEADER_LENGTH];
	in.read(header, 0, Constant.HEADER_LENGTH);
	int length = Integer.valueOf(new String(header));
	byte[] content = new byte[length];
	in.read(content, 0, length);
	String response = new String(content);
	return response;
    }
}
