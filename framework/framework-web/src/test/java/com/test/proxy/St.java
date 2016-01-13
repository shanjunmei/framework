/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.test.proxy;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月25日 上午11:16:54
 * @version
 */
public class St {

    public static void main(String[] args) {

	Thread t=new Worker();
	Thread t1=new Worker();
	t.start();
	t1.start();
    }

    static class Worker extends Thread {

	@Override
	public void run() {
	    Singleton i = Singleton.getIntance();
	    System.out.println(i);
	}
    }
}
