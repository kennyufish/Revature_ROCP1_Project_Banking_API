package com.app.banking.service;

import com.app.banking.exception.BusinessException;
import com.app.banking.model.User;

public interface LoginService {

	public boolean isValidUserCredentials(User user) throws BusinessException;
}
