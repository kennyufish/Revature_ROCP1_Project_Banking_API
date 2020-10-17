package com.app.banking.interceptors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class DepositFilter
 */
public class DepositFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public DepositFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		//check for deposit amount, must be greater than 0;
		if (Integer.parseInt(request.getParameter("depositAmount")) > 0) {
			chain.doFilter(request, response);
		}else {
			out.print("<div><h1 class='topNoticeWarning'>Deposit Amount: '"+request.getParameter("depositAmount")+"' Is Invalid<h1></div>");
			request.getRequestDispatcher("accounts").include(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
