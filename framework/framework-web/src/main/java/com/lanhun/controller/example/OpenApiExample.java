/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.controller.example;

import javax.annotation.Resource;

import com.lanhun.common.annotation.OpenApi;
import com.lanhun.persistence.AgentMapper;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月5日 上午11:00:24
 * @version
 */
@OpenApi("example")
public class OpenApiExample extends BaseController {

    @Resource
    private AgentMapper agentMapper;

    // @Override
    @OpenApi("/sigle")
    public String sigle() {

	return "sigle path";
    }

    @OpenApi("/multi/path/test")
    public void multiPathTest(String hello) {
	System.out.println("multiPathTest" + hello);
    }

    @OpenApi
    @Override
    public String hello() {
	return "example overide";
    }
}
