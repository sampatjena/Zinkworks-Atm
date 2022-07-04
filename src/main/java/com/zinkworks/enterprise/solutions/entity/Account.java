package com.zinkworks.enterprise.solutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    
    @Id
    @Column(name = "account_number")
    private String accountNumber; 
    
    @Column(name ="account_status")
    private String accountStatus;
    
    @Column(name = "account_pin")
    private String accountPin;

    @Column(name = "opening_balance_amount")
    private double openingBalanceAmount;
    
    @Column(name = "overdraft_amount")
    private double overdraftAmount;
    
    public Account() {
        super();
        }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(String accountPin) {
        this.accountPin = accountPin;
    }

    public double getOpeningBalanceAmount() {
        return openingBalanceAmount;
    }

    public void setOpeningBalanceAmount(double openingBalanceAmount) {
        this.openingBalanceAmount = openingBalanceAmount;
    }

    public double getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(double overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    
    
    
    
    }