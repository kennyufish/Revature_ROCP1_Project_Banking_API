package com.app.banking.model;

//The Account model is used to represent a single account for a user
public class Account {
	private int accountId;
	private String username;
	private double balance;
	private AccountStatus status;
	private AccountType type;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(int accountId, String username, double balance, AccountStatus status, AccountType type) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", username=" + username + ", balance=" + balance + ", status=" + status
				+ ", type=" + type + "]";
	}

}
