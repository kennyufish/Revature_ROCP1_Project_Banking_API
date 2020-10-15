package com.app.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.banking.exception.BusinessException;
import com.app.banking.model.Account;
import com.app.banking.service.AccountInforService;
import com.app.banking.service.AccountInforServiceImpl;

/**
 * Servlet implementation class FindAccountByUserIdController
 */
public class FindAccountByUserIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindAccountByUserIdController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// need a session security, add once all functions are done session=null->no
		// access

		response.setContentType("text/html");
		AccountInforService accountService = new AccountInforServiceImpl();
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();

		try {
			List<Account> accountList = accountService.getAccountsByUserId(Integer.parseInt(request.getParameter("userId")));
			if (!accountList.isEmpty()) {
				requestDispatcher = request.getRequestDispatcher("adminInfo.html");
				requestDispatcher.include(request, response);

				out.print("<article>");
				// find account by id block
				out.print("<div><h1 class='subHeading1'>Find/Update Accounts</h1>");
				out.print("<form action='findAccountByAccountId' method='get'>");
				out.print("<table class='findBar'><tr><th>Find Account By Account Id</th>");
				out.print(
						"<td><input style='width:100%' type='number' min='0' step='1' name='accountId' required /></td></tr></table>");
				out.print("<button class='smallButton findButton' type='submit'>Find</button></div>");
				out.print("</form>");
				out.print("<br><br>");
				// find account by id block
				out.print("<form action='findAccountByUserId' method='get'>");
				out.print("<table class='findBar'><tr><th>Find Account By User Id</th>");
				out.print(
						"<td><input style='width:100%' type='number' min='0' step='1' name='userId' required /></td></tr></table>");
				out.print("<button class='smallButton findButton' type='submit'>Find</button></div>");
				out.print("</form>");
				out.print("<br><br>");
				// find account by Status block
				out.print("<form action='findAccountByStatus' method='get'>");
				out.print("<table class='findBar'><tr><th>Find Account By Status</th>");
				out.print("<td><select style='width:100%' name='statusId'>");
				out.print("<option value='' selected disabled hidden>Select Status</option> ");
				out.print("<option value='1'>Pending</option>");
				out.print("<option value='2'>Open</option>");
				out.print("<option value='3'>Closed</option>");
				out.print("<option value='4'>Denied</option>");
				out.print("</select></td></tr></table>");
				out.print("<button class='smallButton findButton' type='submit'>Find</button></div>");
				out.print("</form>");
				out.print("<br><br>");
				//
				out.print("<br><br>");
				// show all account list block
				out.print("<div><h1 class='subHeading1' style='margin-top:20px;'>Accounts List</h1></div>");
				out.print(
						"<div class='smallFont'><table><tr><th>Account ID</th><th>Username</th><th>Type</th><th>Status</th><th>Balance</th>");

				// access controller implement later role == admin
				boolean access = true;
				if (access == true) {
					out.print("<th>Edit</th>");
				}
				
				// list users;
				for (int i = 0; i < accountList.size(); i++) {
					Account account = accountList.get(i);

					out.print("<tr>");
					out.print("<td>" + account.getAccountId() + "</td>");
					out.print("<td>" + account.getUsername() + "</td>");
					out.print("<td>" + account.getType().getType() + "</td>");
					out.print("<td>" + account.getStatus().getStatus() + "</td>");

					// CONVERT balance TO currency
					NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
					String convertedBalance = n.format(account.getBalance());
					out.print("<td>" + convertedBalance + "</td></td>");

					// access controller implement later
					if (access == true) {
						out.print("<form action='viewAccontInfo' method='post'>");
						// pass in parameter
						out.print("<input type='hidden' name='accountId' value='" + account.getAccountId() + "'>");
						out.print("<td><button type='submit'>Edit</button></td></form>");
					}
					out.print("</tr>");
				}
				out.print("</table><div></article>");
			} else {
				requestDispatcher = request.getRequestDispatcher("findUpdateAccounts");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				out.print("<div><h1 class='topNoticeWarning'>CANNOT FIND SUCH ACCOUNT<h1></div>");
				requestDispatcher.include(request, response);
			}
		} catch (BusinessException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>" + e.getMessage() + "<h1></div>");
		} catch (NullPointerException e) {
			requestDispatcher = request.getRequestDispatcher("adminInfo.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			requestDispatcher.include(request, response);
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
