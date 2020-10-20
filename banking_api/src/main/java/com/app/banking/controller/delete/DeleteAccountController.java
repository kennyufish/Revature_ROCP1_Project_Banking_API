package com.app.banking.controller.delete;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.banking.exception.BusinessException;
import com.app.banking.service.AccountInforService;
import com.app.banking.service.AccountInforServiceImpl;

/**
 * Servlet implementation class DeleteAccountController
 */
public class DeleteAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccountController() {
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
		
		String deleteAccount = request.getParameter("deleteAccount");
		try {
			if(deleteAccount.equals("yes")) {
				AccountInforService accountService = new AccountInforServiceImpl();
				requestDispatcher = request.getRequestDispatcher("findUpdateAccounts");
				if (accountService.deleteAccountById(Integer.parseInt(request.getParameter("accountId")))) {
					out.print("<div><h1 class='topNoticeUpdateSuccess'>Account # "+request.getParameter("accountId")+" Deleted<h1></div>");
				}else {
					out.print("<div><h1 class='topNoticeUpdateFail'>Failed To Delete Account # "+request.getParameter("accountId")+"<h1></div>");
				}
				requestDispatcher.include(request, response);
			}else if(deleteAccount.equals("no")) {
				requestDispatcher = request.getRequestDispatcher("viewAccontInfo");
				requestDispatcher.forward(request, response);
			}else {
				requestDispatcher = request.getRequestDispatcher("adminInfo.html");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				out.print("<div><h1 class='topNoticeWarning'>*The requested action is not permitted*<h1></div>");
				requestDispatcher.include(request, response);
			}
		} catch (NumberFormatException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}