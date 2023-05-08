package com.example.virtoney_basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.virtoney_basicbankingapp.Models.Customer;
import com.example.virtoney_basicbankingapp.Models.Transaction;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="BankDB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_CUSTOMER="Customer";
    private static final String TABLE_TRANSACTION = "Transfer";
    private static final String ACCOUNT_NUMBER="AccNum";
    private static final String NAME="Name";
    private static final String EMAIL="Email";
    private static final String PHONE_NUMBER="PhoneNum";
    private static final String CURRENT_BALANCE="CurrentBalance";
    private static final String SERIAL_NUMBER="SrNum",FROM="SenderAccNum",TO="ReceiverAccNum",TRANSFERED_AMT="TransferedAmt";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_CUSTOMER+"("+ACCOUNT_NUMBER+" TEXT PRIMARY KEY,"+
                NAME+" TEXT, "+EMAIL+" TEXT, "+PHONE_NUMBER+"   TEXT , "+CURRENT_BALANCE+
                " INTEGER )");

        db.execSQL("CREATE TABLE "+TABLE_TRANSACTION+"("+SERIAL_NUMBER+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                FROM+" TEXT, "+TO+" TEXT, "+TRANSFERED_AMT+ " INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TRANSACTION);
        onCreate(db);
    }

    //CRUD on Customer Record
    public void addCustomer(String accNum, String name, String email, String phoneNum, int currentBalance){

        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(ACCOUNT_NUMBER,accNum);
        values.put(NAME,name);
        values.put(EMAIL,email);
        values.put(PHONE_NUMBER,phoneNum);
        values.put(CURRENT_BALANCE,currentBalance);
        database.insert(TABLE_CUSTOMER,null,values);
    }

    public ArrayList<Customer> getListOfCustomer(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT " +NAME+", "+EMAIL+", "+CURRENT_BALANCE+ " FROM "+TABLE_CUSTOMER,null);
        ArrayList<Customer> list=new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Customer customer=new Customer(cursor.getString(0),cursor.getString(1),cursor.getInt(2));
            list.add(customer);
            cursor.moveToNext();
        }
        return list;
    }

    public Customer getCustomerDetail(String email){
        SQLiteDatabase db=getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CUSTOMER + " WHERE "
                + EMAIL + " LIKE " +"'"+ email +"%'";

        Cursor c=db.rawQuery(selectQuery,null);
        c.moveToFirst();
        Customer customer= new Customer(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4));
        db.close();
        return customer;
    }

    public int getCurrentBalance(String accNum){
        SQLiteDatabase db=getReadableDatabase();
        String selectQuery = "SELECT "+ CURRENT_BALANCE +" FROM " + TABLE_CUSTOMER +" WHERE " + ACCOUNT_NUMBER + " LIKE " +"'%"+accNum+"'";
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        int amt=cursor.getInt(0);
        db.close();
        return amt;
    }

    public int updateBalance(String accNum , int newBalance){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CURRENT_BALANCE,newBalance);
        int n = db.update(TABLE_CUSTOMER,values,ACCOUNT_NUMBER+" = '"+accNum+"'",null);
        db.close();
        return n;
    }

    //CRUD on Transaction Record
    public void addTransaction(String senderAccNum, String receiverAccNum, int amt){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FROM,senderAccNum);
        values.put(TO,receiverAccNum);
        values.put(TRANSFERED_AMT,amt);

        database.insert(TABLE_TRANSACTION,null,values);
        database.close();
    }

    public ArrayList<Transaction> getAllTransaction(){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM "+TABLE_TRANSACTION+" ",null);
        ArrayList<Transaction> list=new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Transaction transaction=new Transaction(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3) );
            cursor.moveToNext();
            list.add(transaction);
        }
        return list;
    }

}
