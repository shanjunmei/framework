/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.test;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年12月2日 上午9:16:12
 * @version
 */
public class t {

    public static void main(String[] args) {
	t t = new t();
	t.x(t);
	t.xd();
    }

    public void x(t x) {
	x = null;
    }

    public void xd() {
	System.out.println("hello");
    }
}
