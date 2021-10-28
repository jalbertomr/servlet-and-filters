package com.bext.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class Filter_3 implements Filter {

	static class FilteredRequest extends HttpServletRequestWrapper {

        /* These are the characters allowed by the Javascript validation */
        static String allowedChars = "+-0123456789#*";

		public FilteredRequest(HttpServletRequest request) {
			super((HttpServletRequest) request);
		}

        public String sanitize(String input) {
            String result = "";
            for (int i = 0; i < input.length(); i++) {
                if (allowedChars.indexOf(input.charAt(i)) >= 0) {
                    result += input.charAt(i);
                }
            }
            return result;
        }

        public String getParameter(String paramName) {
            String value = super.getParameter(paramName);
            if ("dangerousParamName".equals(paramName)) {
                value = sanitize(value);
            }
            return value;
        }

        public String[] getParameterValues(String paramName) {
            String values[] = super.getParameterValues(paramName);
            if ("dangerousParamName".equals(paramName)) {
                for (int index = 0; index < values.length; index++) {
                    values[index] = sanitize(values[index]);
                }
            }
            return values;
        }
    }
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<br><b>Filter_3</b> Before doFilter(req, resp)</br><hr/>");
		chain.doFilter(new FilteredRequest((HttpServletRequest) request), response);
		out.println("<hr/><br><b>Filter_3</b> After doFilter(req, resp)</br>");
	}


}
