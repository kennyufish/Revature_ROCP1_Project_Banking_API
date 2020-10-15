package com.app.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class FindUpdateUsersController
 */
public class FindUpdateUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindUpdateUsersController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//checked
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();

		try {
			if (session != null) {
				requestDispatcher = request.getRequestDispatcher("adminInfo.html");
				requestDispatcher.include(request, response);
				UserInfoService userService = new UserInfoServiceImpl();
				List<User> userList = userService.getAllUsers();
				System.out.println(userList);

				out.print("<article>");
				out.print("<div><h1 class='subHeading1'>Find/Update Users</h1>");
				out.print("<form action='findUserById' method='get'>");
				out.print("<table class='findBar'><tr><th>Find User By Id</th>");
				out.print(
						"<td><input style='width:100%' type='number' min='0' step='1' name='userId' required /></td></tr></table>");
				out.print(
						"<button class='smallButton findButton' type='submit' name='findUserById'>Find</button></div>");
				out.print("</form>");
				out.print("<br><br><br>");
				out.print("<div><h1 class='subHeading1' style='margin-top:20px;'>Users List</h1></div>");

				out.print(
						"<div class='smallFont'><table><tr><th>User ID</th><th>Username</th><th>User Name</th><th>Email</th><th>Role</th><th>Detail</th>");

				System.out.println("session accessRole:"+session.getAttribute("accessRole"));
				if (session.getAttribute("accessRole").equals("admin")) {
					out.print("<th>Edit</th>");
				}
				out.print("</tr>");
				// list users;
				for (int i = 0; i < userList.size(); i++) {
					User user = userList.get(i);

					out.print("<form action='findAccountByUserId' method='get'>");
					// pass in parameter
					out.print("<input type='hidden' name='userId' value='" + user.getUserId() + "'>");

					out.print("<tr>");
					out.print("<td>" + user.getUserId() + "</td>");
					out.print("<td>" + user.getUsername() + "</td>");
					out.print("<td>" + user.getLastName() + ", " + user.getFirstName() + "</td>");
					out.print("<td>" + user.getEmail() + "</td>");
					out.print("<td>" + user.getRole().getRole() + "</td>");
					out.print("<td><button type='submit'>View</button></td></form>");

					// access controller implement later
					if (session.getAttribute("accessRole").equals("admin")) {
						out.print("<form action='viewUserInfo' method='post'>");
						// pass in parameter
						out.print("<input type='hidden' name='userId' value='" + user.getUserId() + "'>");
						out.print("<td><button type='submit'>Edit</button></td></form>");
					}
					out.print("</tr>");

				}
				out.print("</table><div></article>");
			}else {
				throw new BusinessException("PLEASE LOGIN");
			}
		} catch (BusinessException | UserException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			out.print("<div><h1 class='topNoticeWarning'>"+e.getMessage() +"<h1></div>");
			requestDispatcher.include(request, response);
		} catch (NullPointerException e) {
			// requestDispatcher = request.getRequestDispatcher("adminInfo.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			// requestDispatcher.include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
