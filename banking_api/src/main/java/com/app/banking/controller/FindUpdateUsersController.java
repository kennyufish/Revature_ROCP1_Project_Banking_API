package com.app.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();

		requestDispatcher = request.getRequestDispatcher("adminInfo.html");
		requestDispatcher.include(request, response);
		
		out.print("<article>");
		out.print("<div><h1 class='subHeading1'>Find/Update Users</h1>");
		out.print("<form action='findUserById' method='get'>");
		out.print("<table class='findBar'><tr><th>Find User By Id</th>");
		out.print("<td><input style='width:100%' type='number' min='0' step='1' name='userId' required /></td></tr></table>");
		out.print("<button class='smallButton findButton' type='submit' name='findUserById'>Find</button></div>");
		out.print("</form>");
		out.print("<br><br><br>");
		out.print("<div><h1 class='subHeading1' style='margin-top:20px;'>Users List</h1></div>");
		out.print("</article>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
