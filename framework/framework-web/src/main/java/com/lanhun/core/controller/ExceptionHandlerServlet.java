package com.lanhun.core.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanhun.common.Response;

/**
 * Servlet implementation class ExceptionHandlerServlet
 */
@WebServlet("/exception")
public class ExceptionHandlerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    final static ObjectMapper objectMapper = new ObjectMapper();

    public ExceptionHandlerServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	Throwable e=(Throwable)request.getAttribute("javax.servlet.error.exception");
	exceptionHandle(response,e);
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
}
