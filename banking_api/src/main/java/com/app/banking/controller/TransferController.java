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

import com.app.banking.exception.BusinessException;
import com.app.banking.service.AccountInforService;
import com.app.banking.service.AccountInforServiceImpl;
import com.app.banking.service.AccountTranscationService;
import com.app.banking.service.AccountTranscationServiceImpl;

/**
 * Servlet implementation class TransferController
 */
public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferController() {
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

		AccountInforService accountService = new AccountInforServiceImpl();
		AccountTranscationService accountTranscationService = new AccountTranscationServiceImpl();

		try {
			boolean isSuccess = false;
			if ((accountService.validateAccountById(Integer.parseInt(request.getParameter("targetAccountId"))) 
					&& (Integer.parseInt(request.getParameter("targetAccountId")) != Integer.parseInt(request.getParameter("accountId")))) ) {
				isSuccess = accountTranscationService.transferAccount(Integer.parseInt(request.getParameter("accountId")),
						Integer.parseInt(request.getParameter("targetAccountId")), Double.parseDouble(request.getParameter("transferAmount")));
				if (isSuccess) {
					
					//CONVERT balance TO currency
					NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
					String convertedBalance = n.format(Double.parseDouble(request.getParameter("transferAmount")));
					//
					out.print("<div><h1 class='topNoticeUpdateSuccess'>");
					out.print(convertedBalance + " has been transferred from Account #"
							+ request.getParameter("accountId") + " to Account # " 
							+ request.getParameter("targetAccountId") + "<h1></div>");
				} else {
					out.print("<div><h1 class='topNoticeUpdateFail'>");
					out.print("UNSUCCESSFUL TRANSFER - PLEASE TRY AGAIN" + "<h1></div>");
				}
			}else {
				out.print("<div><h1 class='topNoticeUpdateFail'>");
				out.print("Invalid Recipient ID" + "<h1></div>");
			}
			requestDispatcher = request.getRequestDispatcher("accounts");
			requestDispatcher.include(request, response);
			

		} catch (NullPointerException | NumberFormatException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			out.print(
					"<div><h1 class='topNoticeWarning'>*The requested action is not permitted in transfer*<h1></div>");
			out.print("<div><h1 class='topNoticeWarning'>" + e.getMessage() + "<h1></div>");
			requestDispatcher.include(request, response);
		} catch (BusinessException e) {
			requestDispatcher = request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>" + e.getMessage() + "</span></center>");
		}
	}

}
