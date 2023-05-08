package com.example.virtoney_basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.virtoney_basicbankingapp.Models.Transaction;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TransferMoney extends AppCompatActivity {

    EditText sender, receiver,amt;
    Button payBtn;
    String senderAccNum,receiverAccNum;
    int tranferAmt;
    ProgressBar progressBar;
    final int SENDER=1;
    final int RECEIVER=2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        getSupportActionBar().setTitle(" Virtoney");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sender=findViewById(R.id.senderAccNum);
        receiver=findViewById(R.id.receiverAccNum);
        amt=findViewById(R.id.amt);
        payBtn=findViewById(R.id.payBtn);
        progressBar=findViewById(R.id.progressBar);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(amt.getText()))
                    amt.setError( "Enter the Amount" );
                else if(TextUtils.isEmpty(sender.getText()))
                    sender.setError("Sender's a/c number is required");
                else if(TextUtils.isEmpty(receiver.getText()))
                    receiver.setError("Receiver's a/c number is required");
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    payBtn.setVisibility(View.GONE);

                    senderAccNum = sender.getText().toString();
                    receiverAccNum = receiver.getText().toString();
                    tranferAmt = Integer.parseInt(amt.getText().toString());

                    MyDBHelper db = new MyDBHelper(TransferMoney.this);

                    int senderBalance = db.getCurrentBalance(senderAccNum.substring(senderAccNum.length()-2));
                    int receiverBalance = db.getCurrentBalance(receiverAccNum.substring(receiverAccNum.length()-2));

                    if (senderBalance < tranferAmt) {
                        new SweetAlertDialog(TransferMoney.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Transaction Failed!")
                                .setContentText("Please make sure that your account balance is sufficient to make payments.")
                                .setConfirmText("Okay").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent intent=new Intent(TransferMoney.this,ListOfCustomer.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).show();


                        progressBar.setVisibility(View.GONE);
                        payBtn.setVisibility(View.VISIBLE);
                    }
                    else{
                        //Update Sender's and Receiver's Account Balance
                        int hasSubtracted = db.updateBalance(senderAccNum,senderBalance-tranferAmt);
                        if(hasSubtracted==1)
                            db.updateBalance(receiverAccNum,receiverBalance+tranferAmt );

                        //Insert into Transaction History Table
                        db.addTransaction(senderAccNum,receiverAccNum,tranferAmt);

                        //Dialog Box
                        new SweetAlertDialog(TransferMoney.this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Payment Successfull")
                                .setContentText("INR "+tranferAmt+".00 is successfully transfered to the given account number.")
                                .setConfirmText("Close").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent intent=new Intent(TransferMoney.this,ListOfCustomer.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).show();

                        progressBar.setVisibility(View.GONE);
                        payBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}