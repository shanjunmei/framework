/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */   
package com.lanhun.study.rpc.framework;

import java.lang.reflect.Method;

/**
 * <p>
 * 
 *
 *
 * </p>
 * @author	hz15101769 
 * @date	2015年11月26日 下午2:41:04
 * @version      
 */
public class RealRequest {
    
    private Object target;
    
    private Method method;

    
    public Object getTarget() {
        return target;
    }

    
    public void setTarget(Object target) {
        this.target = target;
    }

    
    public Method getMethod() {
        return method;
    }

    
    public void setMethod(Method method) {
        this.method = method;
    }
    
    

}
 