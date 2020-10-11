package com.app.banking.service;

import com.app.banking.dao.LoginDAO;
import com.app.banking.dao.LoginDAOImpl;
import com.app.banking.exception.BusinessException;
import com.app.banking.model.User;

public class LoginServiceImpl implements LoginService {

	private LoginDAO dao=new LoginDAOImpl();
	
	@Override
	public boolean isValidUserCredentials(User user) throws BusinessException {
		boolean b = false;
		
		if(user!=null && user.getUsername()!=null && user.getPassword()!=null) {
			b=dao.isValidUserCredentials(user);
		}else {
			throw new BusinessException("Invalid Login Credentials");
		}
		
//		if(user!=null && user.getUsername()!=null && user.getPassword()!=null && user.getUsername().matches("[a-z0-9]{10}") && user.getPassword().matches("[a-z*#$@0-9]{10}")) {
//			b=dao.isValidUserCredentials(user);
//		}else {
//			throw new BusinessException("Invalid Login Credentials");
//		}
		
		return b;
	}

}
