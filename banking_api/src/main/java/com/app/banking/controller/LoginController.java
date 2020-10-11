package com.app.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.banking.exception.BusinessException;
import com.app.banking.model.User;
import com.app.banking.service.LoginService;
import com.app.banking.service.LoginServiceImpl;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Accessing LoginController...");
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		LoginService service = new LoginServiceImpl();
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();

		session.setAttribute("user", user);

		try {
			if (service.isValidUserCredentials(user)) {
				requestDispatcher=request.getRequestDispatcher("userInfo");
				requestDispatcher.forward(request, response);
			}
		} catch (BusinessException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("<div><h1 class='topNoticeWarning'>"+e.getMessage()+"<h1></div>");
			requestDispatcher.include(request, response);
		}

	}

}
