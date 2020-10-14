package com.app.banking.dao;

import java.util.List;

import com.app.banking.exception.BusinessException;
import com.app.banking.exception.UserException;
import com.app.banking.model.Account;
import com.app.banking.model.AccountStatus;
import com.app.banking.model.AccountType;
import com.app.banking.model.Role;
import com.app.banking.model.User;

public interface AccountDAO {
	//user can have more than 1 acc
	public List<Account> getAccountByUsername(String username) throws BusinessException;
	
	public Account getAccountById(int accountId) throws BusinessException;
	
	public boolean depositAccountById(int accountId, double depositAmount) throws BusinessException;
	public boolean withdrawAccountById(int accountId, double withdrawAmount) throws BusinessException;
	public boolean transferAccountById(int sourceAccountId, int targetAccountId, double transferAmount) throws BusinessException;
	
	public boolean validateAccountById(int accountId) throws BusinessException;
//	
//	public int addAccount(Account account) throws UserException;
//	
//	//need to get account first before deleting, so we know the id for sure
//	public int deleteAccount(int accountId) throws UserException;
//
//	public int updateAccount(Account account) throws UserException;
//
//	// unique
//	public Account getAccountByAccountId(int accountId) throws UserException;
//
//
//	public List<Account> getAllAccounts() throws UserException;
//
//	public List<Account> getAccountByStatus(AccountStatus status) throws UserException;
//
//	public List<Account> getAccountByType(AccountType AccountType) throws UserException;

}
