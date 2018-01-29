package com.example.antonio.cryptracer.Adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.antonio.cryptracer.Price.Bitcoin_price;
import com.example.antonio.cryptracer.Didactic_units.Bitcoin_didactic;
import com.example.antonio.cryptracer.Models.Model;
import com.example.antonio.cryptracer.R;

import java.util.List;

public class Bitcoin_adapter extends RecyclerView.Adapter<Bitcoin_adapter.BitcoinViewHolder>{

    private List<Model> listModels;
    private Context context;

    public Bitcoin_adapter(List<Model> ListModels, Context context){
        this.listModels = ListModels;
        this.context = context;
    }

    public class BitcoinViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHead;
        public TextView textViewDesc;

        public BitcoinViewHolder(View view){
            super(view);
            textViewHead = (TextView) view.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) view.findViewById(R.id.textViewDesc);
        }
    }

    @Override
    public BitcoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bitcoin_options, parent, false);
        return new BitcoinViewHolder(v);
    }

    @Override
    public void onBindViewHolder( BitcoinViewHolder holder, final int position) {
        Model model = listModels.get(position);
        holder.textViewHead.setText(model.getHead());
        holder.textViewDesc.setText(model.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            Intent bitcoin_didactic = new Intent(context, Bitcoin_didactic.class);
            Intent bitcoin_analytics = new Intent(context, Bitcoin_price.class);
            @Override
            public void onClick(View view) {
                if(listModels.get(position).getHead() == "What is Bitcoin?"){
                    context.startActivity(bitcoin_didactic);
                } if(listModels.get(position).getHead() == "Bitcoin price"){
                    context.startActivity(bitcoin_analytics);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }
}
