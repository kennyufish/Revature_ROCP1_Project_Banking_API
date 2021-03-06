package com.app.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.banking.model.User;

/**
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    //checked
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher=null;
		PrintWriter out=response.getWriter();
		
		try {
			User userSession= (User) session.getAttribute("user");
			session.invalidate();
			requestDispatcher=request.getRequestDispatcher("index.html");
			out.print("<div><h1 class='topNoticeGreen'>You Have Successfully Logged Out '"+userSession.getUsername()+ "'<h1></div>");
			requestDispatcher.include(request, response);
		}catch (NullPointerException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.print("<div><h1 class='topNoticeWarning'>*There Was No User In The Session*<h1></div>");
			requestDispatcher.include(request, response);
		}
		
		
	}

}
