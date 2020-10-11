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
 * Servlet implementation class UserInfoController
 */
public class UserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		UserInfoService service = new UserInfoServiceImpl();
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();

		try {
			// debug
			System.out.println("session id in UserInfoController(): " + session.getId());
			//

			User userSession = (User) session.getAttribute("user");
			User user = service.getUserInfo(userSession.getUsername(), userSession.getPassword());
			requestDispatcher = request.getRequestDispatcher("userInfo.html");
			requestDispatcher.include(request, response);

			out.print("<form action='updateUserInfo' method='post'>");
			out.print("<article><h1>");
			out.print("<table>");

			out.print("</td><tr>");
			out.print("<tr><th>User Role</th><td>");
			out.print(user.getRole().getRole());
			out.print("</td><tr>");
			out.print("<tr><th>User ID</th><td>");
			out.print(user.getUserId());
			out.print("</td><tr>");
			out.print("<tr><th>Username</th><td>");
			out.print(user.getUsername());
			out.print("</td><tr>");
			out.print("<tr><th>User Password</th><td>");
			out.print("<input class='userModifyInput' type='password' name='password' value='");
			out.print(user.getPassword() + "'>");
			out.print("</td><tr>");
			out.print("<tr><th>User First Name</th><td>");
			out.print("<input class='userModifyInput' type='text' name='firstName' value='");
			out.print(user.getFirstName() + "'>");
			out.print("</td><tr>");
			out.print("<tr><th>User Last Name</th><td>");
			out.print("<input class='userModifyInput' type='text' name='lastName' value='");
			out.print(user.getLastName() + "'>");
			out.print("</td><tr>");
			out.print("<tr><th>User Email</th><td>");
			out.print("<input class='userModifyInput' type='text' name='email' value='");
			out.print(user.getEmail() + "'>");

			out.print("</table>");

			out.print("<button type='submit'>Modify Info</button>");
			out.print("</form>");

			out.print("</h1></article>");

		} catch (BusinessException | UserException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>" + e.getMessage() + "</span></center>");
		} catch (NullPointerException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			requestDispatcher.include(request, response);
		}
	}

}