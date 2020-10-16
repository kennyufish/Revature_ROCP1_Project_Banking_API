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

/**
 * Servlet implementation class AddUserAccountController
 */
public class AddUserAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//checked
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// need a session security, add once all functions are done session=null->no
		// access
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();

		try {
			if (session.getAttribute("accessRole").equals("admin")
					|| session.getAttribute("accessRole").equals("employee")) {
				requestDispatcher = request.getRequestDispatcher("adminInfo.html");
				requestDispatcher.include(request, response);

				out.print("<article>");

				out.print("<h1 class='subHeading1'>Register A New User</h1>");
				out.print("<form action='registerUser' method='post'>");
				out.print("<h1><table>");

				out.print("<tr><th>Username</th><td>");
				out.print(
						"<input class='userModifyInput' type='text' name='username' placeholder='Username' required /></td></tr>");

				out.print("<tr><th>User Password</th><td>");
				out.print(
						"<input class='userModifyInput' type='password' name='password' placeholder='Password' required /></td></tr>");

				out.print("<tr><th>User First Name</th><td>");
				out.print(
						"<input class='userModifyInput' type='text' name='firstName' placeholder='First Name' required /></td></tr>");

				out.print("<tr><th>User Last Name</th><td>");
				out.print(
						"<input class='userModifyInput' type='text' name='lastName' placeholder='Last Name' required /></td></tr>");

				out.print("<tr><th>User Email</th><td>");
				out.print(
						"<input class='userModifyInput' type='text' name='email' placeholder='Email' required /></td></tr>");

				out.print("<tr><th>Account Type</th><td><select style='width:100%' name='typeId' required >");
				out.print("<option value='' selected disabled hidden>Choose Type</option>");
				out.print("<option value='1' >Checking</option>");
				out.print("<option value='2' >Savings</option>");
				out.print("</select></td></tr>");

				out.print("<tr><th>Initial Deposit</th><td><span style='position:relative'>$ ");
				out.print(
						"<input class='userModifyInput' style='width:90%' type='number' min='0' step='0.01' name='initialDeposit' placeholder='Initail Deposit' required /></span></td></tr>");

				out.print("</table>");

				out.print("<button class='smallButton' type='submit'>Register User</button>");
				out.print("</h1></form>");

				// register account
				out.print("<h1 class='subHeading1'>Register A New Account</h1>");
				out.print("<form action='registerAccount' method='post'>");
				out.print("<h1><table>");

				out.print("<tr><th>User Id</th><td>");
				out.print(
						"<input class='userModifyInput' type='number' min='0' name='userId' placeholder='User Id' required /></td></tr>");
				out.print("<tr><th>Account Type</th><td><select style='width:100%' name='typeId' required >");
				out.print("<option value='' selected disabled hidden>Choose Type</option>");
				out.print("<option value='1' >Checking</option>");
				out.print("<option value='2' >Savings</option>");
				out.print("</select></td></tr>");

				out.print("<tr><th>Initial Deposit</th><td><span style='position:relative'>$ ");
				out.print(
						"<input class='userModifyInput' style='width:90%' type='number' min='0' step='0.01' name='initialDeposit' placeholder='Initail Deposit' required /></span></td></tr>");

				out.print("</table>");

				out.print("<button class='smallButton' type='submit'>Register Account</button>");
				out.print("</h1></form>");

				out.print("</article>");
			}else {
				throw new BusinessException();
			}

		} catch (NullPointerException | BusinessException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			requestDispatcher.include(request, response);
		}
	}
}
