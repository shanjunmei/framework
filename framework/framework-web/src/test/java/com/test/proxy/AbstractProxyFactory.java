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
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月25日 下午12:01:28
 * @version
 */
public abstract class AbstractProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T buildJdkProxy(Class<T> interfaces) {
	long start = System.currentTimeMillis();

	T proxy = (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[] { interfaces }, new InvocationHandler() {

	    @Override
	    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long start = System.currentTimeMillis();

		System.out.println(method.getName() + " : " + Arrays.toString(args));

		long end = System.currentTimeMillis();
		System.out.println("invoke method [" + method.getName() + "] take " + (end - start) + " ms");
		return null;
	    }
	});

	long end = System.currentTimeMillis();

	System.out.println("invoke method [buildJdkProxy] take " + (end - start) + " ms");
	return proxy;

    }

    @SuppressWarnings("unchecked")
    public static <T> T buildJdkProxy(Class<T> interfaces, final InvocationHandler methodInterceptor) {

	Objects.requireNonNull(interfaces);
	Objects.requireNonNull(methodInterceptor);

	long start = System.currentTimeMillis();

	T proxy = (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[] { interfaces }, methodInterceptor);

	long end = System.currentTimeMillis();

	System.out.println("invoke method [buildJdkProxy] take " + (end - start) + " ms");

	return proxy;

    }

    @SuppressWarnings("unchecked")
    public static <T> T buildCglibProxy(Class<T> interfaces) {
	long start = System.currentTimeMillis();

	Enhancer enhancer = new Enhancer();
	enhancer.setInterfaces(new Class[] { interfaces });
	enhancer.setCallback(new MethodInterceptor() {

	    @Override
	    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		long start = System.currentTimeMillis();

		System.out.println(method.getName() + " : " + Arrays.toString(args));

		long end = System.currentTimeMillis();
		System.out.println("invoke method [" + method.getName() + "] take " + (end - start) + " ms");
		return null;
	    }

	});

	T proxy = (T) enhancer.create();

	long end = System.currentTimeMillis();

	System.out.println("invoke method [buildCglibProxy] take " + (end - start) + " ms");
	return proxy;

    }

}