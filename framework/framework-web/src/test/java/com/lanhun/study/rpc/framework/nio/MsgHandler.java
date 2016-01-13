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

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月26日 下午1:50:20
 * @version
 */
public class MsgHandler {

    public static void send(AsynchronousSocketChannel channel, String src) {
	String lengthStr = appendZero(src.length(), Constant.HEADER_LENGTH);

	src = lengthStr + src;
	ByteBuffer byteBuffer = ByteBuffer.allocate(src.length());
	byteBuffer.put(src.getBytes());
	byteBuffer.flip();
	channel.write(byteBuffer);
    }

    public static String appendZero(int num, int length) {
	// String.valueOf()是用来将其他类型的数据转换为string型数据的
	String tmpString = String.valueOf(num);
	for (int i = tmpString.length(); i < length; i++) {
	    tmpString = "0" + tmpString;
	}
	return tmpString;
    }

    public static String read(AsynchronousSocketChannel channel) throws InterruptedException, ExecutionException {
	ByteBuffer headr = ByteBuffer.allocate(Constant.HEADER_LENGTH);
	channel.read(headr).get();
	headr.flip();

	byte[] hearByte = new byte[headr.limit()];
	headr.get(hearByte);
	int lenth = Integer.valueOf(new String(hearByte));

	ByteBuffer content = ByteBuffer.allocate(lenth);
	channel.read(content).get();
	content.flip();
	byte[] cb = new byte[lenth];
	content.get(cb);
	String n = new String(cb);
	return n;
    }
}
