package com.app.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.banking.database.utility.MySqlConnection;
import com.app.banking.exception.BusinessException;
import com.app.banking.model.User;


public class LoginDAOImpl implements LoginDAO {

	@Override
	public boolean isValidUserCredentials(User user) throws BusinessException {
		boolean b=false;
		
		try(Connection connection=MySqlConnection.getConnection()){
			String sql="select username from user where username=? and password=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				b=true;
				resultSet.close();
			}else {
				resultSet.close();
				throw new BusinessException("Invalid Login Credentials");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //this line you should take it off before going live(production)
			throw new BusinessException("Invalid Credentials");
		}
		
		return b;
	}

}
