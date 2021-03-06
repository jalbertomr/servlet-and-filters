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

public class Filter_2 implements Filter {
    FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String param = filterConfig.getInitParameter("Filter_2_InitParam");
		System.out.println("Init param: " + param);
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   out.println("<b>Filter_2</b> <br/>InitParam: " + filterConfig.getInitParameter("Filter_2_InitParam"));
	   out.println("<br/><br/>Filter_2: Parameters:<br/>");
	   Enumeration<String> parameterNames = request.getParameterNames();
	   if (parameterNames.hasMoreElements()) {
		   while (parameterNames.hasMoreElements()) {
			   String name = parameterNames.nextElement();
			   String value = request.getParameter(name);
			   out.println("Param Name: " + name + ", Value: " + value + "<br/>");
		   }
	   } else {
		   out.println("--- Filter_2 Has Not Init Parameters ---<br/>");
	   }
 	   request.setAttribute("Filter_1_AttributeInserted", "valueAttributeFilter_1");
	   out.println("<br><b>Filter_2</b> Before doFilter(req, resp)</br><hr/>");
	   chain.doFilter(request, response);
	   out.println("<hr/><br><b>Filter_2</b> After doFilter(req, resp)</br>");
	}
}
