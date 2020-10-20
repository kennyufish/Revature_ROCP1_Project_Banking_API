package com.app.banking.service;

import java.util.List;

import com.app.banking.exception.BusinessException;
import com.app.banking.model.Account;

public interface AccountInforService {

	public Account getAccountById(int accountId) throws BusinessException;
	public boolean validateAccountById(int accountId) throws BusinessException;
	public List<Account> getAllAccounts() throws BusinessException;
	public List<Account> getAccountsByUserId(int userId)throws BusinessException;
	public List<Account> getAccountsByStatusId(int statusId) throws BusinessException;
	public boolean editAccountByAdmin(Account account) throws BusinessException;
	public int addAccount(Account account) throws BusinessException;
}
