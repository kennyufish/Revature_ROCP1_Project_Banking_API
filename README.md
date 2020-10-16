# Revature_ROCP1_Project_Banking_API
This is a JEE maven project utilizing servlets, HTML, and MySQL database with RPC Endpoints implementation. It is called as the Banking API that will manage and manipulate the bank accounts of its users. 
-	Standard Users have access to their own accounts and have the ability to make transactions. 
-	Bank’s employees and admins have access to view all users and their associated accounts. 
-	Admins also have the ability to modify user information and register new users/accounts.

## Design Flow
The request will go through three main layers before getting a response. It will first go through a Servlet Layer in which the request is being processed and passed onto the Service Layer. The Service Layer performs validation and provides services before sending the request to the Database Layer. In the Database Layer, that's when the request data are fetched or stored in the corresponding database. The response will then be returned and get dispatched for the client/user.
 - client -> filter -> servlet -> service -> DAO -> database

## Models
- USER: user ID, username, password, name, email, access role
- Account: account ID, balance, status, type

## Functionalities
#### User Side
- Login/logout with sessions
- Edit user/account information
- Deposit, withdraw, transfer funds
#### Admin/Employee Side
- Find users by ID
- Edit/Update users information (ADMIN ONLY)
- Find accounts by ID/Status/User ID
- Edit/Update accounts information (ADMIN ONLY)
#### Test Dump
The test tables are include in the testMySQLDump file, import using MySQL Workbench
## Database Diagram
<img src="/img/database.PNG">
## DEMO
### User Access
<img src="/img/index.PNG">
<img src="/img/userInfo.PNG">
<img src="/img/accountInfo.PNG">
<img src="/img/transferUser.PNG">

### Admin Access
<img src="/img/addNewUser.PNG">
<img src="/img/editUserInfo.PNG">
<img src="/img/findUpdateUsers.PNG">
<img src="/img/findUpdateAccounts.PNG">
