package com.example.virtoney_basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.virtoney_basicbankingapp.Adapters.CustomerAdapter;
import com.example.virtoney_basicbankingapp.Models.Customer;

import java.util.ArrayList;

public class ListOfCustomer extends AppCompatActivity {

    RecyclerView recyclerView;

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()) {
            case R.id.transactionHistory:
                Intent intent1=new Intent(ListOfCustomer.this,TransactionHistory.class);
                startActivity(intent1);
                break;
            case R.id.transferMoney:
                Intent intent2=new Intent(ListOfCustomer.this,TransferMoney.class);
                startActivity(intent2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_customer);
        getSupportActionBar().setTitle("Virtoney");
        recyclerView=(RecyclerView)findViewById(R.id.recylerView);

        MyDBHelper db=new MyDBHelper(this);

 /*     db.addCustomer("VBB452311","Vaibhav","vaibhav@gmail.com","8765432103",75000);
        db.addCustomer("VBB452312","Prachii","prachii@gmail.com","9854366313",210000);
        db.addCustomer("VBB452313","Arviza","arviza@gmail.com","6394832626",60000);
        db.addCustomer("VBB452314","Utkarsh","utkarsh@gmail.com","9897655430",83000);
        db.addCustomer("VBB452301","Mohd Faiz","mohdfaiz@gmail.com","8954836333",70000);
        db.addCustomer("VBB452302","Mohd Faraz","mohdfaraz@gmail.com","9854234333",75000);
        db.addCustomer("VBB452303","Mohd Faiyaaz","mohdfaiyaaz@gmail.com","6384836333",80000);
        db.addCustomer("VBB452304","Mohd Farhan","mohdfarhan@gmail.com","6554836999",90000);
        db.addCustomer("VBB452305","Mohd Adnan","mohdadnan@gmail.com","9954836555",1010000);
        db.addCustomer("VBB452306","Mohd Aman","mohdaman@gmail.com","8963567333",89000);
        db.addCustomer("VBB452307","Mahak Agarwal","mahak@gmail.com","6004836553",860000);
        db.addCustomer("VBB452308","Khushi Khan","khushi@gmail.com","9854833933",110000);
        db.addCustomer("VBB452309","Tabassum","Tabassum@gmail.com","7354836878",100000);
        db.addCustomer("VBB452310","Arohi","arohi@gmail.com","9895489890",60000);
*/
        ArrayList<Customer> list=db.getListOfCustomer();

        CustomerAdapter adapter=new CustomerAdapter(list,ListOfCustomer.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListOfCustomer.this));

    }
}