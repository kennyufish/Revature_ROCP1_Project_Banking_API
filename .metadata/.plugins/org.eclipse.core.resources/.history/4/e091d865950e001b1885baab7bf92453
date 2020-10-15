package com.app.banking.service;

import java.util.List;

import com.app.banking.exception.BusinessException;
import com.app.banking.exception.UserException;
import com.app.banking.model.Account;
import com.app.banking.model.User;

public interface UserInfoService {
	public User getUserInfo(String username) throws BusinessException, UserException;
	public User getUserInfoByUserId(int userId) throws BusinessException, UserException;
	public List<Account> getUserAccount(String username) throws BusinessException, UserException;
	public boolean updateUserInfo(User user) throws BusinessException, UserException;
	public List<User> getAllUsers() throws BusinessException, UserException;
	public boolean editUserByAdmin(User user) throws BusinessException, UserException;
	public int addUser(User user) throws BusinessException, UserException;
}
