package com.bext.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Filter_1 implements Filter {

    FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String param = filterConfig.getInitParameter("Filter_1_InitParam");
		System.out.println("Init param: " + param);
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	   String name = "";
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   out.println("<b>Filter_1</b> <br/>InitParam: " + filterConfig.getInitParameter("Filter_1_InitParam"));
	   out.println("<br/><br/>Filter_1 Parameters:<br/>");
	   Enumeration<String> parameterNames = request.getParameterNames();
	   if (parameterNames.hasMoreElements()) {
		   while (parameterNames.hasMoreElements()) {
			   name = parameterNames.nextElement();
			   String value = request.getParameter(name);
			   out.println("Param Name: " + name + ", Value: " + value + "<br/>");
		   }
	   } else {
		   out.println("--- Filter_1 Has Not Parameters ---<br/>");
	   }
 	   
	   out.println("<br><b>Filter_1</b> Before doFilter(req, resp)</br><hr/>");
	   chain.doFilter(request, response);
	   out.println("<hr/><br><b>Filter_1</b> After doFilter(req, resp)</br>");
	}

}
