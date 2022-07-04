# Zinkworks-Atm
Spring boot code for Atm customer transactions.
Use cases which have been documented and developed are :

1 User (assume any rest client – curl, postman, browser) should be able to request a balance
  check along with maximum withdrawal amount (if any),
2 .User should be able to request a withdrawal. If successful - details of the notes that would
  be dispensed along with remaining balance,
3 If anything goes wrong, user should receive meaningful message, and there should be no
  changes in user’s account.
  
 All the api's are secured endpoints account number pin combination , which are mandatory parameters for making any request.

### Technologies Used
- Java 8
- Spring Boot 2.7.0
- H2 Inmemory DB
- Maven for code compilation and running of application.

Unit testing and code coverage for the Controller , Service and Repository clasess are at 60% .
Details of the testing and code coverage report are available at folder location : 
https://github.com/sampatjena/Zinkworks-Atm/blob/d0d1874237ecb9b4b0384832251c07da21ce78d3/site/jacoco/index.html

Spring rest controller post endpoints for various transactions are :
1. Customer view balance: /v1/api/atm/accountdetails                     
  1.1 Json format request  examples :                             
    
	{
    "accountNumber": "987654321",
    "accountPin": "4321"
     }
  1.2 Json response:
     
	 {
    "accountBalanceAmount": 1230.0,
    "accountOverdraftAmount": 150.0,
    "accountTotalWithdrawlAmountWithOverdraft": 1380.0
   } 
   
2. Customer request to withdraw amount : /v1/api/atm/accountwithdrawl
   2.1 Json format request exmaple :
    
	{
    "accountNumber": "987654321",
    "accountPin": "4321",
    "withdrawlAmount":"80",
    "atmId":"11"
    }
  
  2.2 Json response :
     Currency disbursed : 1 Fifty Euro.1 Twenty Euro.1 Ten Euro.

Github respository information : https://github.com/sampatjena/Zinkworks-Atm.git



