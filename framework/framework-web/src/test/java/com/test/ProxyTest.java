package com.test;
import com.hzins.test.bean.Bean;
import com.hzins.test.bean.Tx;
import com.hzins.test.intaface.X;

import snippet.ProxyGenerator;

/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月6日 上午9:34:05
 * @version
 */
public class ProxyTest {

    public static void main(String[] args) {

	X cglibProxy =  ProxyGenerator.generteByCglib(Tx.class);
	createBeanByProxy(cglibProxy);
	long t = System.currentTimeMillis();
	createBeanByProxy(cglibProxy);
	t = System.currentTimeMillis() - t;
	System.out.println("create total take " + t + "ms");

	X jdkProxy = ProxyGenerator.generteByJdk(Tx.class);
	
	t = System.currentTimeMillis();
	createBeanByProxy(jdkProxy);
	
	t = System.currentTimeMillis() - t;
	System.out.println("create total take " + t + "ms");

    }

    public static Bean createBeanByProxy(X x) {
	long t = System.currentTimeMillis();
	Bean bean = x.createBean("id", "name");
	t = System.currentTimeMillis() - t;
	System.out.println("proxy invoke take :" + t + " ms");
	return bean;
    }

    public static void createBeanByProxyN(X x) {
	long t = System.currentTimeMillis();
	int count = 3;
	for (int i = 0; i < count; i++) {
	    Bean bean = x.createBean("id", "name");
	}

	t = System.currentTimeMillis() - t;
	System.out.println("proxy invoke [" + count + "] time, take :" + t + " ms");
    }

    public static Bean createBean() {
	long t = System.currentTimeMillis();
	Bean bean = new Bean();
	bean.setId("id");
	bean.setName("name");
	t = System.currentTimeMillis() - t;
	System.out.println("direct createBean take :" + t + " ms");
	return bean;

    }

}
