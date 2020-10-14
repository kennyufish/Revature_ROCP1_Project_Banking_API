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
import com.app.banking.service.UserInfoService;
import com.app.banking.service.UserInfoServiceImpl;

/**
 * Servlet implementation class UpdateUserInfoController
 */
public class UpdateUserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		UserInfoService service = new UserInfoServiceImpl();
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		
		//debug
		System.out.println("session id in UserController:" + session.getId());
		System.out.println(session.getAttribute("user").toString());
		
		//get user object from session
		User userSession = (User) session.getAttribute("user");
		
		//update user

		userSession.setEmail(request.getParameter("email"));
		
		System.out.println(request.getParameter("email"));
		userSession.setFirstName(request.getParameter("firstName"));
		userSession.setLastName(request.getParameter("lastName"));
		userSession.setPassword(request.getParameter("password"));
		System.out.println("userSession in UpdateUserInfoController: "+userSession.toString());
		
		try {
			if(service.updateUserInfo(userSession)) {
				out.print("<div><h1 class='topNoticeUpdateSuccess'>Info Updated!<h1></div>");
			}else {
				out.print("<div><h1 class='topNoticeUpdateFail'>Update Failed!<h1></div>");
			}
		} catch (BusinessException | UserException e) {

			out.print("<center><span style='color:red;'>" + e.getMessage() + "</span></center>");
			requestDispatcher = request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);
		}
		requestDispatcher = request.getRequestDispatcher("userInfo");
		requestDispatcher.include(request, response);

		
		
		
	}

}
