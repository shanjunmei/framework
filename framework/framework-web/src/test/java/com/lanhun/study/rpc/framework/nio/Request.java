/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.study.rpc.framework.nio;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月26日 下午2:20:44
 * @version
 */
public class Request {

    private String target;

    private String method;
    
    private Object param;

    
    public String getTarget() {
        return target;
    }

    
    public void setTarget(String target) {
        this.target = target;
    }

    
    public String getMethod() {
        return method;
    }

    
    public void setMethod(String method) {
        this.method = method;
    }

    
    public Object getParam() {
        return param;
    }

    
    public void setParam(Object param) {
        this.param = param;
    }
    
    

}
