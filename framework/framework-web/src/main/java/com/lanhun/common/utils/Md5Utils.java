package com.lanhun.common.utils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static MessageDigest messagedigest = null;

    static {
	try {
	    messagedigest = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
    }

    public static String getFileMD5String(File file) throws IOException {
	FileInputStream in = new FileInputStream(file);
	FileChannel ch = in.getChannel();
	MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
	messagedigest.update(byteBuffer);
	in.close();
	return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(String s) {
	return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
	messagedigest.update(bytes);
	return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
	return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
	StringBuffer stringbuffer = new StringBuffer(2 * n);
	int k = m + n;
	for (int l = m; l < k; l++) {
	    appendHexPair(bytes[l], stringbuffer);
	}
	return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
	char c0 = hexDigits[(bt & 0xf0) >> 4];
	char c1 = hexDigits[bt & 0xf];
	stringbuffer.append(c0);
	stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
	String s = getMD5String(password);
	return s.equals(md5PwdStr);
    }

    public static Object byteToObject(byte[] bytes) {
	Object obj = null;
	try {
	    ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
	    ObjectInputStream oi = new ObjectInputStream(bi);

	    obj = oi.readObject();
	    bi.close();
	    oi.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return obj;
    }

    public static byte[] objectToByte(Object obj) {
	byte[] bytes = null;
	ByteArrayOutputStream bo = null;
	ObjectOutputStream oo = null;
	try {
	    bo = new ByteArrayOutputStream();
	    oo = new ObjectOutputStream(bo);
	    oo.writeObject(obj);

	    bytes = bo.toByteArray();

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		bo.close();
		oo.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return bytes;
    }

    public static String fileHash(byte[] data) {
	String result = null;
	for (int i = 0; i < data.length; i++) {
	    result += Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1);
	}
	return result;
    }
}