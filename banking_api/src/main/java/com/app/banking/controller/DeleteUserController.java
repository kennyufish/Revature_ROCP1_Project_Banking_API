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
import com.app.banking.model.User;
import com.app.banking.service.AccountInforService;
import com.app.banking.service.AccountInforServiceImpl;
import com.app.banking.service.UserInfoService;
import com.app.banking.service.UserInfoServiceImpl;

/**
 * Servlet implementation class DeleteUserController
 */
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		
		String deleteUser = request.getParameter("deleteUser");
		System.out.println(deleteUser);
		try {
			if(deleteUser.equals("yes")) {
				AccountInforService accountService = new AccountInforServiceImpl();
				UserInfoService userService = new UserInfoServiceImpl();
				User user = userService.getUserInfoByUserId(Integer.parseInt(request.getParameter("userId")));
				requestDispatcher = request.getRequestDispatcher("findUpdateUsers");
				if (accountService.deleteAccountByUsername(user.getUsername())) {
					if(userService.deleteUserById(Integer.parseInt(request.getParameter("userId"))))
						out.print("<div><h1 class='topNoticeUpdateSuccess'>User # "+request.getParameter("userId")+" Deleted<h1></div>");
				}else {
					out.print("<div><h1 class='topNoticeUpdateFail'>Failed To Delete User # "+request.getParameter("userId")+"<h1></div>");
				}
				requestDispatcher.include(request, response);
			}else if (deleteUser.equals("no")) {
				requestDispatcher = request.getRequestDispatcher("viewUserInfo");
				requestDispatcher.forward(request, response);
			}else {
				throw new BusinessException("No Access Deleting Account");
			}
			
		}catch (NumberFormatException | BusinessException | UserException e){
			requestDispatcher = request.getRequestDispatcher("adminInfo.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*"+e.getMessage()+"<h1></div>");
			requestDispatcher.include(request, response);
		}
	}

}
