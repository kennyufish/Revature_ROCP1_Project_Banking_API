package com.app.banking.service;

import com.app.banking.dao.AccountDAO;
import com.app.banking.dao.AccountDAOImpl;
import com.app.banking.exception.BusinessException;
import com.app.banking.model.Account;

public class AccountTranscationServiceImpl implements AccountTranscationService {

	private AccountDAO accountDao = new AccountDAOImpl();

	@Override
	public boolean depositAccount(int accountId, double depositAmount) throws BusinessException {
		return accountDao.depositAccountById(accountId, depositAmount);
	}

	@Override
	public boolean withdrawAccount(int accountId, double withdrawAmount) throws BusinessException {
		// check for balance in the account first
		Account account = new Account();
		account = (Account) accountDao.getAccountById(accountId);
		if (account.getBalance() > withdrawAmount) {
			return accountDao.withdrawAccountById(accountId, withdrawAmount);
		} else return false;
		
	}

	@Override
	public boolean transferAccount(int sourceAccountId, int targetAccountId, double transferAmount)
			throws BusinessException {
		
		// check for balance in the account first
		Account account = new Account();
		account = (Account) accountDao.getAccountById(sourceAccountId);
		if (account.getBalance() > transferAmount) {
			return accountDao.transferAccountById(sourceAccountId, targetAccountId, transferAmount);
		} else return false;

	}

}
