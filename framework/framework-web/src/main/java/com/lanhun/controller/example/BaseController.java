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

import com.lanhun.common.annotation.OpenApi;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年12月2日 上午9:13:17
 * @version
 */
public abstract class BaseController {

    public BaseController() {
 	System.out.println(getClass() + " init");
     }

    /**
     * 
     * <p>
     * 
     * 子类可覆盖/重写
     *
     * </p>
     * 
     * @return
     * 
     * @author hz15101769
     * @date 2015年12月2日 下午2:03:22
     * @version
     */
    @OpenApi("hello")
    public String hello() {
	System.out.println("hello");
	return "hello";
    }
}
