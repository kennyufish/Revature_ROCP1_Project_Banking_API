package com.app.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.banking.database.utility.MySqlConnection;
import com.app.banking.exception.BusinessException;
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

		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "UPDATE bankapi.user SET password=?, firstname=?, lastname=?,email=? WHERE user.username=?";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, user.getPassword());
			prepStatement.setString(2, user.getFirstName());
			prepStatement.setString(3, user.getLastName());
			prepStatement.setString(4, user.getEmail());
			prepStatement.setString(5, user.getUsername());
			System.out.println(prepStatement);

			if (prepStatement.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : UserDAOImpl ERROR...");
		}

	}

	@Override
	public List<User> getAllUsers() throws UserException {
		List<User> userList = new ArrayList<User>();
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "SELECT userid,username,password,firstname,lastname,email,user.roleid,role FROM bankapi.user "
					+ "INNER JOIN bankapi.role ON user.roleid = role.roleid;";
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("userid"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setRole(new Role(resultSet.getInt("roleid"), resultSet.getString("role")));
				userList.add(user);
			}
			resultSet.close();

		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : UserDAOImpl():getAllUsers() ERROR: " + e.getMessage());
		}
		return userList;
	}

	@Override
	public User getUserByUserId(int userId) throws UserException {
		User user = new User();
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "SELECT userid,username,password,firstname,lastname,email,user.roleid,role "
					+ "FROM bankapi.user INNER JOIN bankapi.role ON user.roleid=role.roleid WHERE userid = ?";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, userId);

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
	public boolean editUserByAdmin(User user) throws UserException {
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "UPDATE bankapi.user SET password=?, firstname=?, lastname=?,email=?,roleid=? WHERE user.username=?";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, user.getPassword());
			prepStatement.setString(2, user.getFirstName());
			prepStatement.setString(3, user.getLastName());
			prepStatement.setString(4, user.getEmail());
			prepStatement.setInt(5, user.getRole().getRoleId());
			prepStatement.setString(6, user.getUsername());
			System.out.println(prepStatement);

			if (prepStatement.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : UserDAOImpl():editUserByAdmin() ERROR...");
		}
	}

	@Override
	public int addUser(User user) throws UserException {
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "INSERT INTO bankapi.user (password,firstname,lastname,email,roleid,username) VALUES (?,?,?,?,?,?)";

			PreparedStatement prepStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, user.getPassword());
			prepStatement.setString(2, user.getFirstName());
			prepStatement.setString(3, user.getLastName());
			prepStatement.setString(4, user.getEmail());
			prepStatement.setInt(5, user.getRole().getRoleId());
			prepStatement.setString(6, user.getUsername());
			System.out.println(prepStatement);

			prepStatement.executeUpdate();
			ResultSet resultSet = prepStatement.getGeneratedKeys();

			System.out.println(resultSet);
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return 0;
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : UserDAOImpl():addUser() ERROR..." + e.getMessage());
		}
	}

	@Override
	public boolean validateUsername(String username) throws UserException {
		boolean b = false;
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "SELECT userid FROM bankapi.user WHERE username = ?";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, username);

			ResultSet resultSet = prepStatement.executeQuery();

			if (resultSet.next()) {
				b = true;
			} else {
				return b;
			}

			resultSet.close();

		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : validateUsername() ERROR...");
		}
		return b;

	}

	@Override
	public boolean validateUsernEmail(String email) throws UserException {
		boolean b = false;
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "SELECT userid FROM bankapi.user WHERE email = ?";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, email);

			ResultSet resultSet = prepStatement.executeQuery();

			if (resultSet.next()) {
				b = true;
			} else {
				return b;
			}

			resultSet.close();

		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : validateUsername() ERROR...");
		}
		return b;
	}

	@Override
	public boolean deleteUserById(int userId) throws UserException {
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "DELETE FROM bankapi.user WHERE userid=?";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, userId);
			System.out.println(prepStatement);

			if (prepStatement.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new UserException("Contact SYSADMIN : deleteUserById() ERROR...");
		}
	}

}
