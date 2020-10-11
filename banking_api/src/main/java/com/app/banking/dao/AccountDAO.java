package com.app.banking.dao;

import java.util.List;

import com.app.banking.exception.UserException;
import com.app.banking.model.Account;
import com.app.banking.model.AccountStatus;
import com.app.banking.model.AccountType;
import com.app.banking.model.Role;
import com.app.banking.model.User;

public interface AccountDAO {
	public List<Account> getAccountByUsername(String username) throws UserException;
	
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
