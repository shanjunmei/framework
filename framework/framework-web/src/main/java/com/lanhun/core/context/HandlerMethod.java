/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.core.context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月3日 上午11:42:23
 * @version
 */
public class HandlerMethod {

    private Method method;

    private Object target;

    private List<Parameter> parameters;

    public Method getMethod() {
	return method;
    }

    public void setMethod(Method method) {
	this.method = method;
    }

    public Object getTarget() {
	return target;
    }

    public void setTarget(Object target) {
	this.target = target;
    }

    public List<Parameter> getParameters() {
	return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
	this.parameters = parameters;
    }
    
    public Object invoke(Object[] ps) throws IllegalAccessException, InvocationTargetException {
 	getMethod().setAccessible(true);
 	Object ret = getMethod().invoke(getTarget(), ps);
 	return ret;
     }

    @Override
    public String toString() {
	return " [method=" + method  + ", parameters=" + parameters + "]";
    }

}
