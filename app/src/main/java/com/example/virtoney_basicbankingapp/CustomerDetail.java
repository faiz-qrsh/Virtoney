package com.example.virtoney_basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtoney_basicbankingapp.Models.Customer;

public class CustomerDetail extends AppCompatActivity {

    TextView name, email, accNum,phoneNum,balance;
    Button copyBtn;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("Name"));

        name=findViewById(R.id.customerName);
        email=findViewById(R.id.customerEmail);
        accNum=findViewById(R.id.customerAccNum);
        phoneNum=findViewById(R.id.customerPhoneNum);
        balance=findViewById(R.id.customerBalance);
        copyBtn=findViewById(R.id.copybtn);

        MyDBHelper db=new MyDBHelper(this);
        String Email=getIntent().getStringExtra("Email");
        Customer customer=db.getCustomerDetail(Email.substring(0,Email.indexOf('@')));

        name.setText("   Name : "+customer.getName());
        email.setText("   Email : "+customer.getEmail());
        accNum.setText("   Account Number : "+customer.getAccNum());
        phoneNum.setText("   Phone Number : "+customer.getPhoneNum());
        balance.setText("   Account Balance : "+customer.getCurrentBalance());

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    myClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    myClip = ClipData.newPlainText("accNum", accNum.getText().toString().substring(20));
                    myClipboard.setPrimaryClip(myClip);
                    Toast.makeText(CustomerDetail.this, "Account Number Copied", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(CustomerDetail.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}