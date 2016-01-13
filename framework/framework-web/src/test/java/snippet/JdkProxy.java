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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * 
 *
 *
 * </p>
 * @author	hz15101769 
 * @date	2015年11月6日 上午10:22:18
 * @version      
 */
public class JdkProxy implements InvocationHandler{

    private Object target;
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] arg) throws Throwable {
	Object ret=method.invoke(target, arg);
	return ret;
    }
    
    public  JdkProxy(Class<?> target){
	try {
	    this.target=target.newInstance();
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
    
    @SuppressWarnings("unchecked")
    public <T>  T getProxy() {  
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),   
                target.getClass().getInterfaces(), this);  
    }  
}
 