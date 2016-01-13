/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.core.controller;

import java.lang.reflect.InvocationTargetException;

import com.lanhun.core.context.HandlerMethod;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月6日 下午3:22:51
 * @version
 */
public interface ServiceInvoker extends MethodParameterConverter{

    /**
     * 
     * <p>
     * 
     *目标方法加载
     *
     * </p>
     * @param mapping
     * @return
     *  
     * @author	hz15101769 
     * @date	2015年11月6日 下午3:28:05
     * @version
     */
    HandlerMethod loadHandlerMethod(String mapping);

    /**
     * 
     * <p>
     * 
     *目标方法调用
     *
     * </p>
     * @param target
     * @param ps
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *  
     * @author	hz15101769 
     * @date	2015年11月6日 下午3:30:06
     * @version
     */
    Object invoke(HandlerMethod target, Object[] ps) throws IllegalAccessException, InvocationTargetException;

}