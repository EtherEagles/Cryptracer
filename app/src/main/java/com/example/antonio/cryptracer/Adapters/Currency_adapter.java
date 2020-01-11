package com.example.antonio.cryptracer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.antonio.cryptracer.Charts.Analytics;
import com.example.antonio.cryptracer.Didactic_units.Didactic;
import com.example.antonio.cryptracer.Models.Option;
import com.example.antonio.cryptracer.Price.Price;
import com.example.antonio.cryptracer.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio on 29/05/2019.
 */

public class Currency_adapter extends RecyclerView.Adapter<Currency_adapter.CurrencyViewHolder> {

    private List<Option> listOptions = new ArrayList<>();
    private Context context;
    private String coinName;
    private String coinImage;
    public Currency_adapter(List<Option> listOptions, String coinName, String coinImage, Context context){
        this.context = context;
        this.listOptions = listOptions;
        this.coinName = coinName;
        this.coinImage = coinImage;
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewHead;
        TextView textViewDesc;
        public CurrencyViewHolder(View view) {
            super(view);
            textViewHead = (TextView) view.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) view.findViewById(R.id.textViewDesc);
        }
    }
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_currency_cardview, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, final int position) {
        //Define transition data flow here
        Option option = listOptions.get(position);
        final String head = option.getHead();
        final String desc = option.getDesc();
        holder.textViewHead.setText(head);
        holder.textViewDesc.setText(desc);
    }
    @Override
    public int getItemCount() { return listOptions.size(); }
}
