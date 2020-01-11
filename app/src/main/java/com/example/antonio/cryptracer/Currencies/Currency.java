package com.example.antonio.cryptracer.Currencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.antonio.cryptracer.Adapters.Currency_adapter;
import com.example.antonio.cryptracer.Charts.Analytics;
import com.example.antonio.cryptracer.Didactic_units.Didactic;
import com.example.antonio.cryptracer.Models.Option;
import com.example.antonio.cryptracer.Price.Price;
import com.example.antonio.cryptracer.R;
import com.example.antonio.cryptracer.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class Currency extends AppCompatActivity {

    private String[] options = {"About", "Price", "Year Analytics", "Day Analytics", "Hour Analytics"};
    private String[] descriptions = {"Find out more about this currency", "See current price for this currency", "Daily fluctuations for the last year", "Hourly fluctuations for the last day", "Fluctuations from every minute during the last hour"};
    private String coinName;
    private String coinImage;
    private RecyclerView currencyView;
    private RecyclerView.Adapter currencyAdapter;
    private List<Option> listOptions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_recyclerview);
        Bundle extras = getIntent().getExtras();
        coinName = extras.getString("coinName");
        coinImage = extras.getString("coinImage");
        currencyView = (RecyclerView) findViewById(R.id.currency_view);
        currencyView.setHasFixedSize(false);
        currencyView.setLayoutManager(new LinearLayoutManager(this));
        currencyView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        currencyView.setItemAnimator(new DefaultItemAnimator());
        currencyView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), currencyView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final String selected = listOptions.get(position).getHead();
                activityTransitionDataFlow(selected);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        runAdapter(options, descriptions);
    }

    public void activityTransitionDataFlow(String selected){
        Intent didactic = new Intent(getApplicationContext(), Didactic.class);
        Intent price = new Intent(getApplicationContext(), Price.class);
        Intent analytics = new Intent(getApplicationContext(), Analytics.class);
        switch(selected){
            case "About":
                didactic.putExtra("coinName", coinName);
                getApplicationContext().startActivity(didactic);
                break;
            case "Price":
                price.putExtra("coinName", coinName);
                price.putExtra("coinImage", coinImage);
                getApplicationContext().startActivity(price);
                break;
            case "Year Analytics":
                analytics.putExtra("coinName", coinName);
                analytics.putExtra("period", "Yearly");
                getApplicationContext().startActivity(analytics);
                break;
            case "Day Analytics":
                analytics.putExtra("coinName", coinName);
                analytics.putExtra("period", "Daily");
                getApplicationContext().startActivity(analytics);
                break;
            case "Hour Analytics":
                analytics.putExtra("coinName", coinName);
                analytics.putExtra("period", "Hourly");
                getApplicationContext().startActivity(analytics);
                break;
        }
    }
    
    public void runAdapter(String[] options, String[] descriptions){
        for(int i = 0; i < options.length; i++){
            Option option;
            option = new Option(
                    options[i],
                    descriptions[i]
            );
            listOptions.add(option);
        }
        setAdapter();
    }
    public void setAdapter(){
        currencyAdapter = new Currency_adapter(listOptions, coinName, coinImage, this);
        currencyView.setAdapter(currencyAdapter);
    }
}
