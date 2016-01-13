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
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanhun.common.utils.TypeUtils;
import com.lanhun.core.context.TargetLoader;
import com.lanhun.core.context.Parameter;
import com.lanhun.core.context.HandlerMethod;

@Component
public class DefaultServiceInvoker implements ServiceInvoker {

    //注入方式初始化，已满足个性化配置需求
    @Resource
    private  ObjectMapper objectMapper;

    //注入方式初始化，以提供扩展定制 需求
    @Resource
    private TargetLoader targetLoader;

    public DefaultServiceInvoker() {
	super();
    }

    @Override
    public Object invoke(HandlerMethod target, Object[] ps) throws IllegalAccessException, InvocationTargetException {
	return target.invoke(ps);
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public Object[] parameterConvert(HandlerMethod target, InputStream is) throws IOException, JsonParseException, JsonMappingException {
	Object[] ps = new Object[0];// default target method parameter
	List<Parameter> pt = target.getParameters();
	if (pt.size() > 0) {
	    Map<String, Object> paramMap = objectMapper.readValue(is, Map.class);
	    ps = convert(pt, paramMap);
	}
	return ps;
    }

    @Override
    public HandlerMethod loadHandlerMethod(String mapping) {
	HandlerMethod target = targetLoader.load(mapping);
	if (target == null) {
	    throw new RuntimeException("请求目标方法不存在:" + mapping);
	}
	return target;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] parameterConvert(HandlerMethod target, String source) throws IOException, JsonParseException, JsonMappingException {
	Object[] ps = new Object[0];// default target method parameter
	List<Parameter> pt = target.getParameters();
	if (pt.size() > 0) {
	    Map<String, Object> paramMap = objectMapper.readValue(source, Map.class);
	    ps = convert(pt, paramMap);
	}
	return ps;
    }
    @Override
    public Object[] convert(List<Parameter> pt, Map<String, Object> paramMap) {
	Object[] ps;
	ps = new Object[pt.size()];
	for (int i = 0; i < pt.size(); i++) {
	Parameter pb = pt.get(i);
	if (TypeUtils.isWrapperType(pb.getType())) {
	    ps[i] = paramMap.get(pb.getName());
	} else {
	    ps[i] = objectMapper.convertValue(paramMap, pb.getType());
	}
	}
	return ps;
    }

}