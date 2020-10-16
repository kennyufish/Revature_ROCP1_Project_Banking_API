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
import com.app.banking.exception.UserException;
import com.app.banking.model.User;
import com.app.banking.service.LoginService;
import com.app.banking.service.LoginServiceImpl;
import com.app.banking.service.UserInfoService;
import com.app.banking.service.UserInfoServiceImpl;

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
	
	
	//checked
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
		UserInfoService userService = new UserInfoServiceImpl();
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();

		session.setAttribute("user", user);

		try {
			if (service.isValidUserCredentials(user)) {
				User accessUser = userService.getUserInfo(request.getParameter("username"));
				if (accessUser.getRole().getRoleId() == 1 ) {
					session.setAttribute("accessRole", "admin");
					requestDispatcher = request.getRequestDispatcher("addUserAccount");
					requestDispatcher.forward(request, response);
				} else if (accessUser.getRole().getRoleId() == 2) {
					session.setAttribute("accessRole", "employee");
					requestDispatcher = request.getRequestDispatcher("findUpdateUsers");
					requestDispatcher.forward(request, response);
				}else {
					session.setAttribute("accessRole", "standard");
					requestDispatcher = request.getRequestDispatcher("userInfo");
					requestDispatcher.forward(request, response);
				}
			}
		} catch (BusinessException | UserException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("<div><h1 class='topNoticeWarning'>" + e.getMessage() + "<h1></div>");
			requestDispatcher.include(request, response);
		}catch (NullPointerException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			requestDispatcher.include(request, response);
		}

	}

}
