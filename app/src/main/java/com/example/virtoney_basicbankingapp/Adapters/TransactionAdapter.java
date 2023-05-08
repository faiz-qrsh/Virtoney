package com.example.virtoney_basicbankingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtoney_basicbankingapp.Models.Transaction;
import com.example.virtoney_basicbankingapp.R;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    Context context;
    ArrayList<Transaction> list;

    public TransactionAdapter(Context context, ArrayList<Transaction> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.transaction_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(list.size()>0) {
            Transaction transaction = list.get(position);
            holder.count.setText(String.valueOf(transaction.getSerailNum())+".");
            holder.from.setText(transaction.getSenderAcc());
            holder.to.setText(transaction.getReceiverAcc());
            holder.transferedAmt.setText(String.valueOf(transaction.getAmt())+"/-");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView count,transferedAmt, from, to;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            count=itemView.findViewById(R.id.count);
            transferedAmt=itemView.findViewById(R.id.amtTransfered);
            from=itemView.findViewById(R.id.senderAcc);
            to=itemView.findViewById(R.id.receiverAcc);
        }
    }
}
