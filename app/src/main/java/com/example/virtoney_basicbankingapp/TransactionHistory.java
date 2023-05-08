package com.example.virtoney_basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.virtoney_basicbankingapp.Adapters.TransactionAdapter;
import com.example.virtoney_basicbankingapp.Models.Transaction;

import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Transaction> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        getSupportActionBar().setTitle(" Transaction History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=findViewById(R.id.recylerView);

        MyDBHelper db=new MyDBHelper(TransactionHistory.this);
        list=db.getAllTransaction();

        TransactionAdapter adapter=new TransactionAdapter(this,list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}