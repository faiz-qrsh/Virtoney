package com.example.virtoney_basicbankingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtoney_basicbankingapp.CustomerDetail;
import com.example.virtoney_basicbankingapp.Models.Customer;
import com.example.virtoney_basicbankingapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>{
    ArrayList<Customer> list;
    Context context;

    public CustomerAdapter(ArrayList<Customer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_customer,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer customer=list.get(position);
        holder.name.setText(customer.getName());
        holder.email.setText(customer.getEmail());
        String s=String.valueOf(customer.getCurrentBalance());
        holder.balance.setText(" Rs. "+s+"/- ");
        holder.accDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CustomerDetail.class);
                intent.putExtra("Name",customer.getName());
                intent.putExtra("Email",customer.getEmail());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,email,balance;
        ImageView accDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            balance=itemView.findViewById(R.id.balance);
            accDetail=itemView.findViewById(R.id.accDetails);
        }
    }
}
