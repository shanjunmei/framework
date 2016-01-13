package com.lanhun.core.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanhun.common.Response;
import com.lanhun.core.context.ApplicationContextUtils;
import com.lanhun.core.context.HandlerMethod;

/**
 * Servlet implementation class ApiServlet
 */
@WebServlet(description = "请求统一入口", urlPatterns = { "/api/*" })
public class CoreDispatchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    private String urlPatterns = "";

    // 此servlet非spring 托管，无法使用spring 依赖注入，只能手动从servletContenxt中取
    // @Resource
    ServiceInvoker serviceInvoker;

    final static ObjectMapper objectMapper = new ObjectMapper();

    public CoreDispatchServlet() {
	super();
	WebServlet webServlet = CoreDispatchServlet.class.getAnnotation(WebServlet.class);
	urlPatterns = webServlet.urlPatterns()[0];
	serviceInvoker = ApplicationContextUtils.getBean(ServiceInvoker.class);
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
	    long t = System.currentTimeMillis();
	    handle(request, response);
	    t = System.currentTimeMillis() - t;
	    System.out.println("请求 " + request.getRequestURI() + " 耗时 ：" + t + " ms");
	} catch (Exception e) {
	    exceptionHandle(response, e);
	}
    }

    /**
     * 
     * <p>
     * 
     * 异常处理
     *
     * </p>
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws JsonProcessingException
     * 
     * @author hz15101769
     * @date 2015年11月5日 下午2:44:46
     * @version
     */
    private void exceptionHandle(HttpServletResponse response, Throwable e) throws IOException {
	Response rep = Response.error(e.getMessage());
	String retStr = objectMapper.writeValueAsString(rep);
	response.getWriter().append(retStr);
    }

    /**
     * 
     * <p>
     * 
     * 请求处理
     *
     * </p>
     * 
     * @param request
     * @param response
     * @throws IOException
     * 
     * @author hz15101769
     * @date 2015年11月5日 下午2:45:08
     * @version
     */
    private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try {
	    // response.addHeader("Content-type","application/json");
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("application/json");

	    // 获取请求目标方法
	    HandlerMethod target = getHandlerMethod(request);

	    // 请求参数构造
	    Object[] ps = parameterHandle(request, target);

	    // 目标方法调用
	    Object ret = serviceInvoker.invoke(target, ps);

	    // 请求结果处理

	    resultHandle(response, ret);

	} catch (IllegalAccessException e) {
	    throw new RuntimeException("非法访问");
	} catch (IllegalArgumentException e) {
	    throw new RuntimeException("非法参数");
	} catch (InvocationTargetException e) {
	    throw new RuntimeException("目标方法调用失败");
	} catch (JsonParseException | JsonMappingException e1) {
	    throw new RuntimeException("参数格式错误");
	}
    }

    private void resultHandle(HttpServletResponse response, Object ret) throws JsonProcessingException, IOException {
	Response rep = Response.ok(ret);
	String retStr = objectMapper.writeValueAsString(rep);
	response.getWriter().append(retStr);
    }

    private Object[] parameterHandle(HttpServletRequest request, HandlerMethod target) throws IOException, JsonParseException, JsonMappingException {
	InputStream is = request.getInputStream();
	return serviceInvoker.parameterConvert(target, is);
    }

    /**
     * 
     * <p>
     * 
     * 获得请求目标方法
     *
     * </p>
     * 
     * @param request
     * @return
     * 
     * @author hz15101769
     * @date 2015年11月5日 下午2:33:47
     * @version
     */
    private HandlerMethod getHandlerMethod(HttpServletRequest request) {
	String mapping = getRequestMapping(request);
	return serviceInvoker.loadHandlerMethod(mapping);
    }

    private String getRequestMapping(HttpServletRequest request) {
	String uri = request.getRequestURI();
	String contextPath = request.getContextPath();
	String mapping = uri.replace(contextPath + urlPatterns.substring(0, urlPatterns.length() - 2), "");
	if(mapping.trim().length()>1&&mapping.endsWith("/")){
	    mapping=mapping.substring(0, mapping.length()-1);
	}
	if (StringUtils.isBlank(mapping)) {
	    throw new RuntimeException("请求目标方法不可为空");
	}
	return mapping;
    }

}
