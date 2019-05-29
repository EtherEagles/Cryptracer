package com.example.antonio.cryptracer.Adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.antonio.cryptracer.Charts.Multichart;
import com.example.antonio.cryptracer.Price.Bitcoin_price;
import com.example.antonio.cryptracer.Didactic_units.Bitcoin_didactic;
import com.example.antonio.cryptracer.Models.Option;
import com.example.antonio.cryptracer.R;

import java.util.List;

public class Bitcoin_adapter extends RecyclerView.Adapter<Bitcoin_adapter.BitcoinViewHolder>{

    private List<Option> listOptions;
    private Context context;

    public Bitcoin_adapter(List<Option> listOptions, Context context){
        this.listOptions = listOptions;
        this.context = context;
    }

    public void passParameters(Intent multichart, String coinName, String period) {
        multichart.putExtra("coinName", coinName);
        multichart.putExtra("period", period);
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
        Option option = listOptions.get(position);
        holder.textViewHead.setText(option.getHead());
        holder.textViewDesc.setText(option.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            Intent multichart = new Intent(context, Multichart.class);
            Intent bitcoin_didactic = new Intent(context, Bitcoin_didactic.class);
            Intent bitcoin_price = new Intent(context, Bitcoin_price.class);
            @Override
            public void onClick(View view) {
                if(listOptions.get(position).getHead() == "What is Bitcoin?"){
                    context.startActivity(bitcoin_didactic);
                } if(listOptions.get(position).getHead() == "Bitcoin current price"){
                    context.startActivity(bitcoin_price);
                } if(listOptions.get(position).getHead() == "Bitcoin last year chart") {
                    passParameters(multichart, "BTC", "Yearly");
                    context.startActivity(multichart); //Displays chart with fluctuations taking place every day for the last year
                } if(listOptions.get(position).getHead() == "Bitcoin last day chart") {
                    passParameters(multichart, "BTC", "Daily");
                    context.startActivity(multichart); //Displays chart with fluctuations taking place every hour for the last day
                } if(listOptions.get(position).getHead() == "Bitcoin last hour chart") {
                    passParameters(multichart, "BTC", "Hourly");
                    context.startActivity(multichart); //Displays chart with fluctuations taking place every minute for the last hour
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOptions.size();
    }
}
