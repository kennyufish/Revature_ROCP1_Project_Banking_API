package com.app.banking.controller.delete;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteWarningController
 */
public class DeleteWarningController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteWarningController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		
		String deleteAccess = request.getParameter("deleteAccess");
		if(deleteAccess.equals("deleteAccount")) {
			requestDispatcher = request.getRequestDispatcher("adminInfo.html");
			requestDispatcher.include(request, response);
			out.print("<article>");
			out.print("<h1 class='subHeading1'>Are You Sure To Delete Account # "+request.getParameter("accountId")+"</h1>");
			out.print("<form action='deleteAccount' method='post'>");
			out.print("<input type='hidden' name='accountId' value='"+request.getParameter("accountId")+"'>");
			out.print("<div style='width:800px;display:flex;justify-content:center;'>");
			out.print("<button style='background-color:red;width:50%;' class='smallButton' type='submit' name='deleteAccount' value='yes'>Delete Account</button>");
			out.print("<button style='background-color:green;width:50%;' class='smallButton' type='submit' name='deleteAccount' value='no'>Go Back</button>");
			out.print("</div></form></article>");
		}else if (deleteAccess.equals("deleteUser")) {
			requestDispatcher = request.getRequestDispatcher("adminInfo.html");
			requestDispatcher.include(request, response);
			out.print("<article>");
			out.print("<h1 class='subHeading1'>Are You Sure To Delete User # "+request.getParameter("userId")+"</h1>");
			out.print("<form action='deleteUser' method='post'>");
			out.print("<input type='hidden' name='userId' value='"+request.getParameter("userId")+"'>");
			out.print("<div style='width:800px;display:flex;justify-content:center;'>");
			out.print("<button style='background-color:red;width:50%;' class='smallButton' type='submit' name='deleteUser' value='yes'>Delete User</button>");
			out.print("<button style='background-color:green;width:50%;' class='smallButton' type='submit' name='deleteUser' value='no'>Go Back</button>");
			out.print("</div></form></article>");
		}else {
			requestDispatcher = request.getRequestDispatcher("adminInfo.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			requestDispatcher.include(request, response);
		}
	}

}
