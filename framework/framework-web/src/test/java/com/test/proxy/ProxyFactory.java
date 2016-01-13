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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzins.test.bean.Bean;
import com.lanhun.common.utils.TypeUtils;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月25日 上午10:31:37
 * @version
 */
public class ProxyFactory extends AbstractProxyFactory {

    private static final class Invoker implements InvocationHandler {

	final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	    long start = System.currentTimeMillis();

	    System.out.println(Arrays.toString(args));

	    Object ret = null;
	    if (method.getReturnType() != null && void.class != method.getReturnType()) {
		if (!TypeUtils.isWrapperType(method.getReturnType())) {
		    ret = objectMapper.readValue("{}", method.getReturnType());
		} else {
		    ret = method.getReturnType().newInstance();
		}
	    }

	    long end = System.currentTimeMillis();
	    System.out.println("invoke  method [" + method.getName() + "] take : " + (end - start) + " ms");

	    return ret;
	}
    }

    public static void main(String[] args) {

	System.out.println();

	ITest jdkProxy = buildJdkProxy(ITest.class, new Invoker());

	String ret = jdkProxy.hello("hello");

	System.out.println(ret);

	Bean b = jdkProxy.h();

	System.out.println(b);

	b = jdkProxy.h();
	System.out.println(b);

    }

}
