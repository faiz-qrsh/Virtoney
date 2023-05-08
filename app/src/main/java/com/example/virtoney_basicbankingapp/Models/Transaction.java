package com.example.virtoney_basicbankingapp.Models;

public class Transaction {
    int serailNum, amt;
    String receiverAcc, senderAcc;

    public Transaction(int serailNum, String receiverAcc, String senderAcc, int amt) {
        this.serailNum = serailNum;
        this.amt = amt;
        this.receiverAcc = receiverAcc;
        this.senderAcc = senderAcc;
    }

    public int getSerailNum() {
        return serailNum;
    }

    public int getAmt() {
        return amt;
    }

    public String getReceiverAcc() {
        return receiverAcc;
    }

    public String getSenderAcc() {
        return senderAcc;
    }

    public void setSerailNum(int serailNum) {
        this.serailNum = serailNum;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public void setReceiverAcc(String receiverAcc) {
        this.receiverAcc = receiverAcc;
    }

    public void setSenderAcc(String senderAcc) {
        this.senderAcc = senderAcc;
    }
}
