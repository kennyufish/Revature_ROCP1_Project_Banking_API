package com.app.banking.service;

import com.app.banking.dao.AccountDAO;
import com.app.banking.dao.AccountDAOImpl;
import com.app.banking.dao.UserDAO;
import com.app.banking.dao.UserDAOImpl;
import com.app.banking.exception.BusinessException;
import com.app.banking.model.Account;
import com.app.banking.model.User;

public class AccountInforServiceImpl implements AccountInforService {


	private UserDAO userDao = new UserDAOImpl();
	private AccountDAO accountDao = new AccountDAOImpl();
	
	@Override
	public Account getAccountById(int accountId) throws BusinessException {
		Account account = new Account();
		account = (Account) accountDao.getAccountById(accountId);
		return account;
	}

	@Override
	public boolean validateAccountById(int accountId) throws BusinessException {
		return accountDao.validateAccountById(accountId);
	}

}
