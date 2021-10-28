package com.bext.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -2063502095847255667L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<b>=== TestServlet running. doGet ===</b><br/>");
		Enumeration<String> parameterNames = req.getParameterNames();
		if (parameterNames.hasMoreElements()) {
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = req.getParameter(name);
				out.println("name: " + name + ", value: " + value + "<br/>");
			}
		} else {
			out.println("--- TestServlet Has Not Parameters ---<br/>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<b>=== TestServlet running. doPost ===</b><br/>");
		Enumeration<String> parameterNames = request.getParameterNames();
		if (parameterNames.hasMoreElements()) {
			while (parameterNames.hasMoreElements()) {
				String name = parameterNames.nextElement();
				String value = request.getParameter(name);
				out.println("name: " + name + ", value: " + value + "<br/>");
			}
		} else {
			out.println("--- TestServlet Has Not Parameters ---<br/>");
		}
	}
}
