package com.app.banking.service;

import com.app.banking.exception.BusinessException;
import com.app.banking.model.Account;

public interface AccountTranscationService {
	public boolean depositAccount(int accountId, double depositAmount) throws BusinessException;
	public boolean withdrawAccount(int accountId, double withdrawAmount) throws BusinessException;
	public boolean transferAccount(int sourceAccountId, int targetAccountId, double transferAmount) throws BusinessException;
}
