package com.example.antonio.cryptracer.Adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.antonio.cryptracer.Charts.Bitcoin_Chart_Day;
import com.example.antonio.cryptracer.Charts.Bitcoin_Chart_Hour;
import com.example.antonio.cryptracer.Charts.Bitcoin_Chart_Minute;
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
            Intent bitcoin_price = new Intent(context, Bitcoin_price.class);
            Intent bitcoin_chart_day = new Intent(context, Bitcoin_Chart_Day.class);
            Intent bitcoin_chart_hour = new Intent(context, Bitcoin_Chart_Hour.class);
            Intent bitcoin_chart_minute = new Intent(context, Bitcoin_Chart_Minute.class);
            @Override
            public void onClick(View view) {
                if(listModels.get(position).getHead() == "What is Bitcoin?"){
                    context.startActivity(bitcoin_didactic);
                } if(listModels.get(position).getHead() == "Bitcoin current price"){
                    context.startActivity(bitcoin_price);
                } if(listModels.get(position).getHead() == "Bitcoin last year chart") {
                    context.startActivity(bitcoin_chart_day); //Displays chart with fluctuations taking place every day for the last year
                } if(listModels.get(position).getHead() == "Bitcoin last day chart") {
                    context.startActivity(bitcoin_chart_hour); //Displays chart with fluctuations taking place every hour for the last day
                } if(listModels.get(position).getHead() == "Bitcoin last hour chart") {
                    context.startActivity(bitcoin_chart_minute); //Displays chart with fluctuations taking place every minute for the last hour
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listModels.size();
    }
}
