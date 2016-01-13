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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年10月30日 下午2:46:09
 * @version
 */

public class HttpUtils {

    public static void main(String[] args) {
	List<String> res=new ArrayList<String>();
	for (int i = 0; i < 10; i++) {
	    String response=post();
	    res.add(response);
	  //  System.out.println(response);
	}
	
	System.out.println(res);
	
	
	
    }

    public static String post() {
	long t=System.currentTimeMillis();
	String urlString = "http://localhost:8080/ServerTest/api";
	String params = "";
	URL url = null;
	try {
	    url = new URL(urlString);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	   // conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
	   conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); 
	    //conn.setRequestMethod("POST");
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	   conn.setConnectTimeout(1000 * 50);
	   // conn.getOutputStream().write(params.getBytes("utf8"));
	    conn.getOutputStream().flush();
	    conn.getOutputStream().close();

	    StringBuffer sb = new StringBuffer();
	   // InputStream in = conn.getInputStream();
	    BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
	   // System.out.println(in.available());
	    int responseCode=conn.getResponseCode();
	    while (reader.ready()) {
		sb.append(reader.readLine());
	    }
	    reader.close();
	    System.out.println("sb:" + sb.toString());
	    //in.close();
	    t=System.currentTimeMillis()-t;
		System.out.println("take "+ t+" ms with status : "+responseCode);
	    return sb.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
	

    }

}
