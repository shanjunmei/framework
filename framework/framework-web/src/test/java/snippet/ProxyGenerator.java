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

import java.lang.reflect.Method;

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
 * @date 2015年11月6日 下午12:21:54
 * @version
 */
public class ProxyGenerator {

    @SuppressWarnings("unchecked")
    public static <T> T generteByCglib(Class<?> target) {
	long t = System.currentTimeMillis();
	Enhancer enhancer = new Enhancer();
	enhancer.setSuperclass(target);
	enhancer.setInterfaces(target.getInterfaces());
	enhancer.setCallback(new MethodInterceptor() {

	    @Override
	    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
		Object ret = proxy.invokeSuper(obj, arg);
		return ret;
	    }
	});
	T proxy = (T) enhancer.create();
	t = System.currentTimeMillis() - t;
	System.out.println("Cglib Proxy generate take " + t + " ms");
	return proxy;
    }

    @SuppressWarnings("unchecked")
    public static <T> T generteByJdk(Class<?> target) {
	long t = System.currentTimeMillis();
	JdkProxy proxy = new JdkProxy(target);

	t = System.currentTimeMillis() - t;
	System.out.println("Jdk Proxy generate take :" + t + " ms");
	return (T) proxy.getProxy();
    }
    

}
