package com.app.banking.service;

import java.util.ArrayList;
import java.util.List;

import com.app.banking.dao.AccountDAO;
import com.app.banking.dao.AccountDAOImpl;
import com.app.banking.dao.UserDAO;
import com.app.banking.dao.UserDAOImpl;
import com.app.banking.exception.BusinessException;
import com.app.banking.exception.UserException;
import com.app.banking.model.Account;
import com.app.banking.model.AccountStatus;
import com.app.banking.model.AccountType;
import com.app.banking.model.User;

public class UserInfoServiceImpl implements UserInfoService {

	private UserDAO userDao = new UserDAOImpl();
	private AccountDAO accountDao = new AccountDAOImpl();

	@Override
	public User getUserInfo(String username, String password) throws BusinessException, UserException {
		// we can have validation in this service, skipping for simplicity
		User user = new User();
		user = (User) userDao.getUserByUsername(username);
		return user;
	}

	@Override
	public List<Account> getUserAccount(String username) throws BusinessException, UserException {
		List<Account> accountList = accountDao.getAccountByUsername(username);
		return accountList;
	}

	@Override
	public boolean updateUserInfo(User user) throws BusinessException, UserException {

		// updatedUser object
		User updatedUser = (User) user;
		return userDao.updateUser(updatedUser);
	}

}