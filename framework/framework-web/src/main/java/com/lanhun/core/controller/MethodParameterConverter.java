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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lanhun.core.context.HandlerMethod;
import com.lanhun.core.context.Parameter;

/**
 * <p>
 * 
 *
 *
 * </p>
 * @author	hz15101769 
 * @date	2015年11月20日 下午1:58:02
 * @version      
 */
public interface MethodParameterConverter {

    /**
     * 
     * <p>
     * 
     *
     * 请求参数转换
     * </p>
     * 
     * @param target
     * @param is
     * @return
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     * 
     * @author hz15101769
     * @date 2015年11月6日 下午3:27:46
     * @version
     */
    Object[] parameterConvert(HandlerMethod target, InputStream is) throws IOException, JsonParseException, JsonMappingException;
    
    /**
     * 
     * <p>
     * 
     *
     *
     * </p>
     * @param target
     * @param source
     * @return
     * @throws IOException
     * @throws JsonParseException
     * @throws JsonMappingException
     *  
     * @author	hz15101769 
     * @date	2015年11月20日 下午5:47:50
     * @version
     */
    Object[] parameterConvert(HandlerMethod target, String source) throws IOException, JsonParseException, JsonMappingException;

    /**
     * 
     * <p>
     * 
     *
     *
     * </p>
     * @param pt
     * @param paramMap
     * @return
     *  
     * @author	hz15101769 
     * @date	2015年11月20日 下午5:55:48
     * @version
     */
    Object[] convert(List<Parameter> pt, Map<String, Object> paramMap);

}