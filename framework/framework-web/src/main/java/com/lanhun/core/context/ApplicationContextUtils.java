package com.lanhun.core.context;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
/**
 * <p>
 * 
 *
 *
 * </p>
 * @author	hz15101769 
 * @date	2015年11月3日 上午10:59:03
 * @version      
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static  ApplicationContext contenxt;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	contenxt=applicationContext;
    }
    public static <T> T getBean(Class<T> type){
	return contenxt.getBean(type);
    }

}
 