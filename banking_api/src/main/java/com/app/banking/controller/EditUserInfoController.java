package com.app.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.banking.exception.BusinessException;
import com.app.banking.exception.UserException;
import com.app.banking.model.Role;
import com.app.banking.model.User;
import com.app.banking.service.UserInfoService;
import com.app.banking.service.UserInfoServiceImpl;

/**
 * Servlet implementation class EditUserInfoController
 */
public class EditUserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//need a session security, add once all functions are done session=null->no access
		
		response.setContentType("text/html");
		UserInfoService service = new UserInfoServiceImpl();
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		

		UserInfoService userService = new UserInfoServiceImpl();
		
		try {
			User user = userService.getUserInfoByUserId(Integer.parseInt(request.getParameter("userId")));
			
			user.setEmail(request.getParameter("email"));
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setPassword(request.getParameter("password"));
			
			Role role = new Role();
			role.setRoleId(Integer.parseInt(request.getParameter("roleid")));
			user.setRole(role);
			if(service.editUserByAdmin(user)) {
				out.print("<div><h1 class='topNoticeUpdateSuccess'>Info Updated!<h1></div>");
			}else {
				out.print("<div><h1 class='topNoticeUpdateFail'>Update Failed!<h1></div>");
			}
		} catch (BusinessException | UserException e) {
			out.print("<center><span style='color:red;'>" + e.getMessage() + "</span></center>");
			requestDispatcher = request.getRequestDispatcher("admin.html");
			requestDispatcher.include(request, response);
		}
		requestDispatcher = request.getRequestDispatcher("viewUserInfo");
		requestDispatcher.include(request, response);
	}

}