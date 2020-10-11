package com.app.banking.dao;

import com.app.banking.exception.BusinessException;
import com.app.banking.model.User;

public interface LoginDAO {

	public boolean isValidUserCredentials(User user) throws BusinessException;
}
