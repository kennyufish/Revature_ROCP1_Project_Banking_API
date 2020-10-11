package com.app.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.banking.database.utility.MySqlConnection;
import com.app.banking.exception.UserException;
import com.app.banking.model.Role;
import com.app.banking.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User getUserByUsername(String username) throws UserException {

		User user = new User();
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "SELECT userid,username,password,firstname,lastname,email,user.roleid,role "
					+ "FROM bankapi.user INNER JOIN bankapi.role ON user.roleid=role.roleid WHERE username = ?";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, username);

			ResultSet resultSet = prepStatement.executeQuery();

			if (resultSet.next()) {
				user.setUserId(resultSet.getInt("userid"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setRole(new Role(resultSet.getInt("roleid"), resultSet.getString("role")));
			}
			
			resultSet.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : UserDAOImpl ERROR...");
		}

		return user;
	}

	@Override
	public boolean updateUser(User user) throws UserException {

		System.out.println("inside updateUser...");
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "UPDATE bankapi.user SET password=?, firstname=?, lastname=?,email=? WHERE user.username=?";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, user.getPassword());
			prepStatement.setString(2, user.getFirstName());
			prepStatement.setString(3, user.getLastName());
			prepStatement.setString(4, user.getEmail());
			prepStatement.setString(5, user.getUsername());
			System.out.println(prepStatement);
			
			if(prepStatement.executeUpdate() != 0) {
				return true;
			}else {
				return false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : UserDAOImpl ERROR...");
		}

	}

}