package com.example.virtoney_basicbankingapp.Models;

public class Customer {
    String accNum, name,email,phoneNum;
    int currentBalance;

    public Customer(String accNum, String name, String email, String phoneNum, int currentBalance) {
        this.accNum = accNum;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.currentBalance = currentBalance;
    }
    public Customer(){}

    public Customer(String name, String email, int currentBalance) {
        this.name = name;
        this.email = email;
        this.currentBalance = currentBalance;
    }

    public String getAccNum() {
        return accNum;
    }


    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }
}
