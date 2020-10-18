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
import com.app.banking.model.Account;
import com.app.banking.model.User;
import com.app.banking.service.AccountInforService;
import com.app.banking.service.AccountInforServiceImpl;

/**
 * Servlet implementation class ViewAccontInfoController
 */
public class ViewAccontInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAccontInfoController() {
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
		// need a session security, add once all functions are done session=null->no
		// access
		
		//testing
		HttpSession session = request.getSession();
		//
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		
		User accountUser = new User();

		AccountInforService accountService = new AccountInforServiceImpl();

		try {
			Account account = accountService.getAccountById(Integer.parseInt(request.getParameter("accountId")));
			
			//testing
			accountUser.setUsername(account.getUsername());
			session.setAttribute("user", accountUser);
			session.setAttribute("accessRole", "admin");
			//
			requestDispatcher = request.getRequestDispatcher("adminInfo.html");
			requestDispatcher.include(request, response);

			out.print("<form action='editAccountInfo' method='post'>");
			out.print("<article>");
			out.print("<h1 class='subHeading1'>Edit Account Information</h1>");
			out.print("<h1><table>");

			// pass in parameter
			out.print("<input type='hidden' name='accountId' value='" + account.getAccountId()+ "'>");

			out.print("<tr><th>Account ID</th><td>" + account.getAccountId() + "</td></tr>");
			out.print("<tr><th>Username</th><td>" + account.getUsername() + "</td></tr>");

			out.print("<tr><th>Account Status</th><td><select style='width:100%' name='statusId'>");
			out.print("<option value='1' ");
			if (account.getStatus().getStatusId() == 1)
				out.print("selected='selected'>Pending</option>");
			else
				out.print(">Pending</option>");
			out.print("<option value='2' ");
			if (account.getStatus().getStatusId() == 2)
				out.print("selected='selected'>Open</option>");
			else
				out.print(">Open</option>");
			out.print("<option value='3' ");
			if (account.getStatus().getStatusId() == 3)
				out.print("selected='selected'>Closed</option>");
			else
				out.print(">Closed</option>");
			out.print("<option value='4' ");
			if (account.getStatus().getStatusId() == 4)
				out.print("selected='selected'>Denied</option>");
			else
				out.print(">Denied</option>");
			out.print("</select></td></tr>");
			
			out.print("<tr><th>Account Type</th><td><select style='width:100%' name='typeId'>");
			out.print("<option value='1' ");
			if (account.getStatus().getStatusId() == 1)
				out.print("selected='selected'>Checking</option>");
			else
				out.print(">Checking</option>");
			out.print("<option value='2' ");
			if (account.getStatus().getStatusId() == 2)
				out.print("selected='selected'>Savings</option>");
			else
				out.print(">Savings</option>");
			out.print("</select></td></tr>");
			
			out.print("<tr><th>Account Type</th><td><input type='number' step='0.01' name='balance' value='"+account.getBalance()+"'/></td></tr>");
			
			out.print("</table>");
			out.print("<button class='smallButton' type='submit'>Edit Account</button>");
			out.print("</form>");
			
			out.print("<form action='accounts' method='post'>");
			//hidden parameter to carry to accounts page
			out.print("<input type='hidden' name='accountId' value='");
			out.print(account.getAccountId()+"'>");
			out.print("<input type='hidden' name='accountBalance' value='");
			out.print(account.getBalance()+"'>");
			
			out.print("<button class='smallButton' type='submit' name='accountAccess' value='deposit'>Deposit</button>");
			out.print("<button class='smallButton' type='submit' name='accountAccess' value='withdraw'>Withdraw</button>");
			out.print("<button class='smallButton' type='submit' name='accountAccess' value='transfer'>Transfer</button>");
			out.print("</form>");
			
			//delete function
			out.print("<br><br>");
			out.print("<form action='deleteAccountByID' method='post'>");
			out.print("<button style='background-color:red;' class='smallButton' type='submit' name='deleteAccount'>Delete Account</button>");
			out.print("<sapn style='color:red' >*CAUTION: this process is irreversible*</span>");
			out.print("</form>");

			out.print("</h1></article>");

		} catch (BusinessException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			out.print("<center><span style='color:red;'>" + e.getMessage() + "</span></center>");
			requestDispatcher.include(request, response);
		} catch (NullPointerException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			requestDispatcher.include(request, response);
		}
	}

}
