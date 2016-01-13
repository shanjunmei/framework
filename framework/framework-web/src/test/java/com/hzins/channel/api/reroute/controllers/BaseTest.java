/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.hzins.channel.api.reroute.controllers;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * 测试用例基础类
 * </p>
 * 
 * @author hz15041177
 * @date 2015-5-19 上午8:28:38
 * @version
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:resource/spring/*.xml" })
public abstract class BaseTest {

   // private MockMvc mockMvc;

   
    // @Autowired
    // private ApiInterceptor apiInterceptor;

   /* @Resource
    private AgentMapper agentMapper;*/
    
    @Before
    public void setUp() {


    }

    public void buildMockMvc(String method, String args) throws Exception {
	long start = System.currentTimeMillis();
	//System.out.println(result.getResponse().getContentAsString());
	long end = System.currentTimeMillis();
	System.out.println("此次测试用例方法：" + method + "&&  花费时间：" + (end - start) + "毫秒");
    }

 
}
