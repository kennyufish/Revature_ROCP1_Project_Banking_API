package com.app.banking.dao;

import java.util.List;

import com.app.banking.exception.UserException;
import com.app.banking.model.User;

public interface UserDAO {
	
	public boolean updateUser(User user) throws UserException;
	public boolean editUserByAdmin(User user) throws UserException;
	
	public User getUserByUsername(String username) throws UserException;
	public User getUserByUserId(int userId) throws UserException;
	
	public List<User> getAllUsers() throws UserException;
	public int addUser(User user) throws UserException;
	public boolean validateUsername(String username) throws UserException;

}
