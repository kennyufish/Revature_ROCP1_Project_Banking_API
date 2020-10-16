package com.app.banking.interceptors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.app.banking.exception.BusinessException;
import com.app.banking.exception.UserException;
import com.app.banking.service.UserInfoService;
import com.app.banking.service.UserInfoServiceImpl;

/**
 * Servlet Filter implementation class RegisterUserFilter
 */
public class RegisterUserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RegisterUserFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		UserInfoService userService = new UserInfoServiceImpl();
		
		PrintWriter out = response.getWriter();
		try {
			//check for existing user and email
			if(!userService.validateUsername(request.getParameter("username"))) {
				if(!userService.validateUserEmail(request.getParameter("email"))) {
					chain.doFilter(request, response);
				}else {
					out.print("<div><h1 class='topNoticeWarning'>Cannot Use Email: '"+request.getParameter("email")+"' , User Exists With Such Email<h1></div>");
					request.getRequestDispatcher("addUserAccount").include(request, response);
				}
			}else {
				out.print("<div><h1 class='topNoticeWarning'>Username: '"+request.getParameter("username")+"' Exists, Please Try A Different Username<h1></div>");
				request.getRequestDispatcher("addUserAccount").include(request, response);
			}
		} catch (BusinessException | UserException | IOException | ServletException e) {
			out.print("<div><h1 class='topNoticeWarning'>FATAL ERROR<h1></div>");
			request.getRequestDispatcher("addUserAccount").include(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
