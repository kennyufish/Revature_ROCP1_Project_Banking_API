package com.app.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.banking.database.utility.MySqlConnection;
import com.app.banking.exception.BusinessException;
import com.app.banking.exception.UserException;
import com.app.banking.model.Account;
import com.app.banking.model.AccountStatus;
import com.app.banking.model.AccountType;
import com.app.banking.model.Role;
import com.app.banking.model.User;

public class AccountDAOImpl implements AccountDAO {
	
	@Override
	public List<Account> getAccountByUsername(String username) throws BusinessException {
		List<Account> accountList = new ArrayList<Account>();
		
		try (Connection connection = MySqlConnection.getConnection()) {
			System.out.println("inside AccDAOImpl();");
			String sql = "SELECT firstname,lastname,user.username,accountid,balance,account.statusid,status,account.typeid,type FROM bankapi.user " + 
					"INNER JOIN bankapi.account ON account.username=user.username " + 
					"INNER JOIN bankapi.accountstatus ON account.statusid=accountstatus.statusid " + 
					"INNER JOIN bankapi.accounttype ON account.typeid=accounttype.typeid " + 
					"WHERE user.username=?;";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, username);

			System.out.println("about to execute()....");
			ResultSet resultSet = prepStatement.executeQuery();

			System.out.println("executeQuery()....");
			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountId(resultSet.getInt("accountId"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setStatus(new AccountStatus(resultSet.getInt("statusId"),resultSet.getString("status")));
				account.setType(new AccountType(resultSet.getInt("typeId"),resultSet.getString("type")));
				account.setUsername(username);
				accountList.add(account);
			}

			System.out.println("output result");
			resultSet.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Contact SYSADMIN : AccountDAOImpl():getAccountByUsername() ERROR...");
		}

		return accountList;
	}

	@Override
	public Account getAccountById(int accountId) throws BusinessException {
		
		Account account = new Account();
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "SELECT username,accountid,balance,account.statusid,status,account.typeid,type FROM bankapi.account " + 
					"INNER JOIN bankapi.accountstatus ON account.statusid=accountstatus.statusid " + 
					"INNER JOIN bankapi.accounttype ON account.typeid=accounttype.typeid " + 
					"WHERE accountid=?;";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, accountId);

			ResultSet resultSet = prepStatement.executeQuery();

			if (resultSet.next()) {
				account.setAccountId(resultSet.getInt("accountId"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setStatus(new AccountStatus(resultSet.getInt("statusId"),resultSet.getString("status")));
				account.setType(new AccountType(resultSet.getInt("typeId"),resultSet.getString("type")));
				account.setUsername(resultSet.getString("username"));
			}
			
			resultSet.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Contact SYSADMIN : AccountDAOImpl():getAccountById() ERROR...");
		}

		return account;
	}

	@Override
	public boolean depositAccountById(int accountId, double depositAmount) throws BusinessException {
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "UPDATE bankapi.account SET balance = balance + ? WHERE accountid=?;";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setDouble(1, depositAmount);
			prepStatement.setInt(2, accountId);

			if(prepStatement.executeUpdate() != 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Contact SYSADMIN : AccountDAOImpl():depositAccountById() ERROR...");
		}

	}

	@Override
	public boolean withdrawAccountById(int accountId, double withdrawAmount) throws BusinessException {
		try (Connection connection = MySqlConnection.getConnection()) {
			String sql = "UPDATE bankapi.account SET balance = (balance - ?) WHERE accountid=?;";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setDouble(1, withdrawAmount);
			prepStatement.setInt(2, accountId);

			if(prepStatement.executeUpdate() != 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Contact SYSADMIN : AccountDAOImpl():withdrawAccountById() ERROR...");
		}
	}

	@Override
	public boolean transferAccountById(int sourceAccountId, int targetAccountId, double transferAmount)
			throws BusinessException {
		try (Connection connection = MySqlConnection.getConnection()) {
			//first withdraw the money from sourceAccount
			if(this.withdrawAccountById(sourceAccountId, transferAmount)){
				if(this.depositAccountById(targetAccountId, transferAmount)) {
					return true;
				}else {
					//if can't deposit, rewind
					this.depositAccountById(sourceAccountId, transferAmount);
					return false;
				}
			}else 
				return false;
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Contact SYSADMIN : AccountDAOImpl():transferAccountById() ERROR...");
		}
	}

	@Override
	public boolean validateAccountById(int accountId) throws BusinessException {
		try (Connection connection = MySqlConnection.getConnection()) {
			Account account = new Account();
			
			String sql = "SELECT username,accountid,balance,account.statusid,status,account.typeid,type FROM bankapi.account " + 
					"INNER JOIN bankapi.accountstatus ON account.statusid=accountstatus.statusid " + 
					"INNER JOIN bankapi.accounttype ON account.typeid=accounttype.typeid " + 
					"WHERE accountid=?;";

			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setDouble(1, accountId);

			ResultSet resultSet = prepStatement.executeQuery();

			if (resultSet.next()) {
				account.setAccountId(resultSet.getInt("accountId"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setStatus(new AccountStatus(resultSet.getInt("statusId"),resultSet.getString("status")));
				account.setType(new AccountType(resultSet.getInt("typeId"),resultSet.getString("type")));
				account.setUsername(resultSet.getString("username"));
			}else {
				resultSet.close();
				return false;
			}

			resultSet.close();
			//check to see if the account is open and such account exists
			if(account.getStatus().getStatusId() == 2) {
				return true;
			}else {
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Contact SYSADMIN : AccountDAOImpl():validateAccountById() ERROR...");
		}
	}


}
