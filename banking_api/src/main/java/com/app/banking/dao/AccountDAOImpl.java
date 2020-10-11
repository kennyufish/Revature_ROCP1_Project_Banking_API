package com.app.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.banking.database.utility.MySqlConnection;
import com.app.banking.exception.UserException;
import com.app.banking.model.Account;
import com.app.banking.model.AccountStatus;
import com.app.banking.model.AccountType;
import com.app.banking.model.Role;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> getAccountByUsername(String username) throws UserException {
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
			throw new UserException("Contact SYSADMIN : AccountDAOImpl ERROR...");
		}

		return accountList;
	}

}