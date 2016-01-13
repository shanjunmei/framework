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
 * @date 2015年11月25日 上午11:10:03
 * @version
 */
public class Singleton {

    private Singleton() {
    }

    private final static class Holder {

	private final static Singleton intance = new Singleton();

    }

    public static Singleton getIntance() {
	return Holder.intance;
    }

    public void hello() {
	System.out.println("hello");
    }
}
