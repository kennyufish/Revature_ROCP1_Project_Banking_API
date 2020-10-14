package com.app.banking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.banking.exception.BusinessException;
import com.app.banking.exception.UserException;
import com.app.banking.model.Account;
import com.app.banking.model.User;
import com.app.banking.service.AccountInforService;
import com.app.banking.service.AccountInforServiceImpl;
import com.app.banking.service.UserInfoService;
import com.app.banking.service.UserInfoServiceImpl;

/**
 * Servlet implementation class AccountTranscationController
 */
public class AccountTranscationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountTranscationController() {
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
		HttpSession session = request.getSession(false);

		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		
		UserInfoService userService = new UserInfoServiceImpl();
		AccountInforService accountService = new AccountInforServiceImpl();

		try {
			
			// dispatch
			requestDispatcher = request.getRequestDispatcher("userInfo.html");
			requestDispatcher.include(request, response);
			//session data
			User userSession = (User) session.getAttribute("user");
			// get user and specific account objects 
			User user = userService.getUserInfo(userSession.getUsername(), userSession.getPassword());
			Account account = accountService.getAccountById(Integer.parseInt(request.getParameter("accountId")));
			
			// article layer
			out.print("<article>");
			
			//determine the access type- deposit -withdraw -transfer
			String accessType = request.getParameter("accountAccess");
			if (accessType.equals("deposit")) {
				out.print("<h1 class='subHeading1'>Transcation - Deposit</h1>");
				out.print("<h2>");
				
				out.print("<table>");

				out.print("<form action='deposit' method='post'>");
				out.print("<tr><th><b>User</b></th>");
				out.print("<td colspan='3'>" + user.getLastName() + " , " + user.getFirstName() + "</td></tr>");
				out.print("<tr><th><b>Account ID</b></th>");
				out.print("<td>" + account.getAccountId() + "</td>");
				out.print("<th><b>Account Balance</b></th>");
				
				//CONVERT balance TO currency
				NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
				String convertedBalance = n.format(account.getBalance());
				out.print("<td>" + convertedBalance + "</td></tr>");
				//
				
				//hidden parameter to carry to accounts page
				out.print("<input type='hidden' name='accountId' value='"+account.getAccountId()+"'>");
				//
				out.print("<th><b>Deposit Amount </b></th>");
				out.print("<td colspan ='2'>" + "<span style='position:relative'>$ ");
				out.print("<input style='width:90%' type='number' min='0.00' step='0.01' name='depositAmount' required /></span></td>");
				out.print("<td><button class='smallButtonFullWidth' type='submit' name='accountAccess' value='deposit' >Deposit</button></td></tr>");
				out.print("</form>");
				
				out.print("</table></article>");
				
		
				out.print("</h2>");
			} else if (accessType.equals("withdraw")) {
				out.print("<h1 class='subHeading1'>Transcation - Withdraw</h1>");
				out.print("<h2>");
				
				out.print("<table>");

				out.print("<form action='withdraw' method='post'>");
				out.print("<tr><th><b>User</b></th>");
				out.print("<td colspan='3'>" + user.getLastName() + " , " + user.getFirstName() + "</td></tr>");
				out.print("<tr><th><b>Account ID</b></th>");
				out.print("<td>" + account.getAccountId() + "</td>");
				out.print("<th><b>Account Balance</b></th>");
				
				//CONVERT balance TO currency
				NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
				String convertedBalance = n.format(account.getBalance());
				out.print("<td>" + convertedBalance + "</td></tr>");
				//
				
				//hidden parameter to carry to accounts page
				out.print("<input type='hidden' name='accountId' value='"+account.getAccountId()+"'>");
				//
				out.print("<th><b>Withdraw Amount </b></th>");
				out.print("<td colspan ='2'>" + "<span style='position:relative'>$ ");
				out.print("<input style='width:90%' type='number' min='0.00' step='0.01' name='withdrawAmount' required /></span></td>");
				out.print("<td><button class='smallButtonFullWidth' type='submit' name='accountAccess' value='withdraw' >Withdraw</button></td></tr>");
				out.print("</form>");
				
				out.print("</table></article>");
		
				out.print("</h2>");
			} else if (accessType.equals("transfer")) {
				out.print("<h1 class='subHeading1'>Transcation - Transfer</h1>");
				out.print("<h2>");
				
				out.print("<table>");

				out.print("<form action='transfer' method='post'>");
				out.print("<tr><th><b>User</b></th>");
				out.print("<td colspan='3'>" + user.getLastName() + " , " + user.getFirstName() + "</td></tr>");
				out.print("<tr><th><b>Account ID</b></th>");
				out.print("<td>" + account.getAccountId() + "</td>");
				out.print("<th><b>Account Balance</b></th>");
				
				//CONVERT balance TO currency
				NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
				String convertedBalance = n.format(account.getBalance());
				out.print("<td>" + convertedBalance + "</td></tr>");
				//
				
				//hidden parameter to carry to accounts page
				out.print("<input type='hidden' name='accountId' value='"+account.getAccountId()+"'/>");
				//
				out.print("<tr><th colspan='1'><b>Recipient Account ID</b></th>");
				out.print("<td colspan ='2'>" + "<span style='position:relative'># ");
				out.print("<input style='width:90%' type='number' min='0' step='1' name='targetAccountId' required /></span></td>");
				out.print("<td rowspan='2'><button class='smallButtonFullWidth' type='submit' name='accountAccess' value='transfer' >Transfer</button></td></tr>");
				
				out.print("<tr><th><b>Transfer Amount </b></th>");
				out.print("<td colspan ='2'>" + "<span style='position:relative'>$ ");
				out.print("<input style='width:90%' type='number' min='0.00' step='0.01' name='transferAmount' required /></span></td></tr>");
				out.print("</form>");
				
				out.print("</table></article>");
			} else {
				throw new BusinessException("*Invalid Access*");
			}
			out.print("</h1></article>");

		} catch (NullPointerException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
			requestDispatcher.include(request, response);
		} catch (BusinessException | UserException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>" + e.getMessage() + "</span></center>");
		}
	}

}
