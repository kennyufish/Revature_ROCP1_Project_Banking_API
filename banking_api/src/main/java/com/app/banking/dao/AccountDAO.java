package com.app.banking.dao;

import java.util.List;

import com.app.banking.exception.BusinessException;
import com.app.banking.model.Account;

public interface AccountDAO {
	//user can have more than 1 acc
	public List<Account> getAccountByUsername(String username) throws BusinessException;
	
	public Account getAccountById(int accountId) throws BusinessException;
	
	public boolean depositAccountById(int accountId, double depositAmount) throws BusinessException;
	public boolean withdrawAccountById(int accountId, double withdrawAmount) throws BusinessException;
	public boolean transferAccountById(int sourceAccountId, int targetAccountId, double transferAmount) throws BusinessException;
	
	public boolean validateAccountById(int accountId) throws BusinessException;

	public List<Account> getAllAccounts() throws BusinessException;

	public List<Account> getAccountByUserId(int userId) throws BusinessException;

	public List<Account> getAccountsByStatusId(int statusId) throws BusinessException;

	public boolean editAccountByAdmin(Account account) throws BusinessException;

	public int addAccount(Account account) throws BusinessException;

	public boolean deleteAccount(int accountId) throws BusinessException;

	public boolean deleteAccountByUsername(String username) throws BusinessException;

}
