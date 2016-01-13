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

import com.hzins.test.bean.Bean;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月25日 上午10:30:29
 * @version
 */
public interface ITest {

    public String hello(String hello);

    public void hello();
    
    public void hello(String ...hello);
    
    public Bean h();
    
}
