/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
package com.lanhun.core.context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import com.lanhun.common.annotation.OpenApi;
import com.lanhun.common.utils.ReflectUtils;

/**
 * <p>
 * 
 *
 *
 * </p>
 * 
 * @author hz15101769
 * @date 2015年11月3日 上午11:33:12
 * @version
 */
@Component
public class AnnotationTargetLoader
	implements ApplicationContextAware, TargetLoader {

    private static final Map<String, HandlerMethod> handlerMethods = new HashMap<String, HandlerMethod>();

    private final static Logger logger = LogManager.getLogger();

    ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	detectHandleMethods(applicationContext);
	//if (logger.isDebugEnabled()) {
	    for (Entry<String, HandlerMethod> e : handlerMethods.entrySet()) {
		String registe_info = e.getKey() + " registed to :" + e.getValue();
		logger.info(registe_info);
	    }
	//}
    }

    /**
     * 
     * <p>
     * 
     * 请求方法注册
     *
     * </p>
     * 
     * @param applicationContext
     * 
     * @author hz15101769
     * @date 2015年11月6日 下午4:12:31
     * @version
     */
    public void detectHandleMethods(ApplicationContext applicationContext) {

	Map<String, Object> beansCache = applicationContext.getBeansWithAnnotation(OpenApi.class);
	for (Entry<String, Object> e : beansCache.entrySet()) {
	    Object target = e.getValue();
	    try {
		Class<?> validClass = ReflectUtils.fetchValidClass(target);// 获取代理类的真实class

		String baseMapping = "";
		OpenApi base = validClass.getAnnotation(OpenApi.class);
		if (base != null) {
		    baseMapping = base.value();
		}
		Method[] methods = findMethodByAnnotation(validClass, OpenApi.class);
		for (Method method : methods) {
		    handleMethod(target, method, baseMapping);

		    // TODO 向服务中心注册
		}
	    } catch (ClassNotFoundException e1) {
		throw new RuntimeException(e1);
	    }

	}
    }

    public void handleMethod(Object target, Method method, String baseMapping) {

	OpenApi reqmapping = method.getAnnotation(OpenApi.class);
	String urlMappling = method.getName();
	if (StringUtils.isNotBlank(reqmapping.value())) {
	    urlMappling = reqmapping.value();
	}

	baseMapping = prepareProcessMapping(baseMapping);

	urlMappling = prepareProcessMapping(urlMappling);

	urlMappling = baseMapping + urlMappling;

	if (handlerMethods.containsKey(urlMappling)) {
	    throw new RuntimeException("mapping " + urlMappling + " has registed");
	}

	HandlerMethod targetBean = new HandlerMethod();
	targetBean.setMethod(method);
	targetBean.setTarget(target);

	String[] pnames = parameterNameDiscoverer.getParameterNames(method);
	Class<?>[] ptypes = method.getParameterTypes();
	List<Parameter> mp = new ArrayList<Parameter>();

	Parameter pb = null;
	for (int i = 0; i < pnames.length; i++) {
	    pb = new Parameter();
	    pb.setName(pnames[i]);
	    pb.setType(ptypes[i]);
	    mp.add(pb);
	}
	targetBean.setParameters(mp);

	handlerMethods.put(urlMappling, targetBean);
    }

    /**
     * 
     * <p>
     * 
     * 预处理urlMapping 如果urlMapping 不以 / 开头 则添加 如果urlMapping 以 / 结束，则移除
     * 
     * </p>
     * 
     * @param urlMappling
     * @return
     * 
     * @author hz15101769
     * @date 2015年12月2日 上午9:50:07
     * @version
     */
    private String prepareProcessMapping(String urlMappling) {
	if (urlMappling != null && urlMappling.trim().length() > 1 && !urlMappling.startsWith("/")) {
	    urlMappling = "/" + urlMappling;
	}
	if (urlMappling != null && urlMappling.trim().length() > 1 && urlMappling.endsWith("/")) {
	    urlMappling = urlMappling.substring(0, urlMappling.length() - 1);
	}
	return urlMappling;
    }

    public Method[] findMethodByAnnotation(Class<?> cls, Class<? extends Annotation> annotation) {
	Method[] methods = cls.getMethods();

	List<Method> methodList = new ArrayList<Method>();

	for (Method method : methods) {
	    Annotation t = method.getAnnotation(annotation);
	    if (t != null) {
		methodList.add(method);
	    }
	}
	return methodList.toArray(new Method[methodList.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hzins.test.context.TargetLoader#load(java.lang.String)
     */
    @Override
    public HandlerMethod load(String mapping) {
	return handlerMethods.get(mapping);
    }
}
