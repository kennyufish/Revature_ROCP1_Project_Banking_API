package com.app.banking.service;


import java.util.List;

import com.app.banking.dao.AccountDAO;
import com.app.banking.dao.AccountDAOImpl;
import com.app.banking.dao.UserDAO;
import com.app.banking.dao.UserDAOImpl;
import com.app.banking.exception.BusinessException;
import com.app.banking.exception.UserException;
import com.app.banking.model.Account;
import com.app.banking.model.User;

public class UserInfoServiceImpl implements UserInfoService {

	private UserDAO userDao = new UserDAOImpl();
	private AccountDAO accountDao = new AccountDAOImpl();

	@Override
	public User getUserInfo(String username) throws BusinessException, UserException {
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

	@Override
	public List<User> getAllUsers() throws BusinessException, UserException {
		List<User> userList = userDao.getAllUsers();
		return userList;
	}

	@Override
	public User getUserInfoByUserId(int userId) throws BusinessException, UserException {
		// TODO Auto-generated method stub
		User user = userDao.getUserByUserId(userId);
		return user;
	}

	@Override
	public boolean editUserByAdmin(User user) throws BusinessException, UserException {
		// updatedUser object
		User updatedUser = (User) user;
		return userDao.editUserByAdmin(updatedUser);
	}

	@Override
	public int addUser(User user) throws BusinessException, UserException {
			return userDao.addUser(user);
	}

	@Override
	public boolean validateUsername(String username) throws BusinessException, UserException {
		return userDao.validateUsername(username);
	}

	@Override
	public boolean validateUserEmail(String email) throws BusinessException, UserException {
		return userDao.validateUsernEmail(email);
	}


}
