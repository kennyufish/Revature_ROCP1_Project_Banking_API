package com.app.banking.service;

import com.app.banking.exception.BusinessException;
import com.app.banking.model.Account;

public interface AccountInforService {

	public Account getAccountById(int accountId) throws BusinessException;
	public boolean validateAccountById(int accountId) throws BusinessException;
}
