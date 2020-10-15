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
import com.app.banking.model.Account;
import com.app.banking.model.AccountType;
import com.app.banking.model.Role;
import com.app.banking.model.User;
import com.app.banking.service.AccountInforService;
import com.app.banking.service.AccountInforServiceImpl;
import com.app.banking.service.UserInfoService;
import com.app.banking.service.UserInfoServiceImpl;

/**
 * Servlet implementation class RegisterAccountController
 */
public class RegisterAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		UserInfoService userService = new UserInfoServiceImpl();
		AccountInforService accountService = new AccountInforServiceImpl();

		try {
			User user = userService.getUserInfoByUserId(Integer.parseInt(request.getParameter("userId")));
			Account account = new Account();
			account.setUsername(user.getUsername());
			account.setBalance(Double.parseDouble(request.getParameter("initialDeposit")));
			AccountType type = new AccountType();
			type.setTypeId(Integer.parseInt(request.getParameter("typeId")));
			account.setType(type);

			int accountId = accountService.addAccount(account);
			if (accountId != 0) {
				out.print("<div><h1 class='topNoticeUpdateSuccess'>");
				out.print("Account ID #" + accountId + " has been successfully submitted<h1></div>");

			} else {
				out.print("<div><h1 class='topNoticeUpdateFail'>");
				out.print("UNSUCCESSFUL SUBMISSION - PLEASE TRY AGAIN" + "<h1></div>");
			}
			requestDispatcher = request.getRequestDispatcher("addUserAccount");
			requestDispatcher.include(request, response);
		} catch (BusinessException | UserException e) {
			requestDispatcher = request.getRequestDispatcher("addUserAccount");
			requestDispatcher.include(request, response);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>" + e.getMessage() + "<h1></div>");
		} catch (NullPointerException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			requestDispatcher.include(request, response);
		}

	}

}