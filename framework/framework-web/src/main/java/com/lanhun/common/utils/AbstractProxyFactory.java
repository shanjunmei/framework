package com.lanhun.common.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

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
    public static <T> T buildJdkProxy(Class<T> interfaces, final InvocationHandler methodInterceptor) {
	return (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[] { interfaces }, methodInterceptor);

    }

}