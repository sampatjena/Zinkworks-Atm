# Zinkworks-Atm
Spring boot code for Atm customer transactions.
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

