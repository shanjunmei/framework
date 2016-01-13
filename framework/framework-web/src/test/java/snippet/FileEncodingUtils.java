/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package snippet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年10月30日 下午4:53:37
 * @version
 */
public class FileEncodingUtils {

    public static void main(String[] args) throws Exception {
	String pathname="d:/error-code.properties";
	File file=new File(pathname);
	FileInputStream fis = new FileInputStream(file);
	guessEncode(fis);
    }

    public static String guessEncode(InputStream bin) {
	String code = null;
	int[] head = new int[4];
	String hexString="";
	try {
	    for (int i = 0; i < 4; i++) {
		head[i] = bin.read();
		hexString+=Integer.toHexString(head[i])+" ";
	    }

	    bin.skip(0);
	    
	    System.out.println(hexString);
	    code = "ANSI";
	    if (head[0] == 0xef && head[1] == 0xbb && head[2] == 0xbf) {
		code = "UTF-8";

	    } else if (head[0] == 0xfe && head[1] == 0xff) {
		code = "utf-16/ucs2, little endian";

	    } else if (head[0] == 0xff && head[1] == 0xfe) {
		code = "utf-16/ucs2, big endian";

	    } else if (head[0] == 0xff && head[1] == 0xfe && head[2] == 0x0 && head[3] == 0x0) {
		code = "UTF-32/ucs4, little endian";

	    } else if (head[0] == 0x0 && head[1] == 0x0 && head[2] == 0xfe && head[3] == 0xff) {
		code = "UTF-32/ucs4, big endian";
	    }
	} catch (Exception e) {
	    System.out.println(e);
	}
	System.out.println(code);
	return code;
    }
}
