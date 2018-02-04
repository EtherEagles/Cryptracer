package com.example.antonio.cryptracer.Currencies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.antonio.cryptracer.Adapters.Bitcoin_adapter;
import com.example.antonio.cryptracer.Models.Model;
import com.example.antonio.cryptracer.R;

import java.util.ArrayList;
import java.util.List;

public class Bitcoin extends AppCompatActivity {

    private RecyclerView bitcoinView;
    private RecyclerView.Adapter bitcoinAdapter;

    private List<Model> listModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin);

        bitcoinView = (RecyclerView) findViewById(R.id.bitcoin_view);
        bitcoinView.setHasFixedSize(true);
        bitcoinView.setLayoutManager(new LinearLayoutManager(this));

        listModels = new ArrayList<>();
        String[] bitcoin_options = {"What is Bitcoin?", "Bitcoin current price", "Bitcoin last year chart", "Bitcoin last day chart", "Bitcoin last hour chart"};
        String[] bitcoin_descriptions = {"Get a taste of the most valuable cryptocurrency", "See current price for bitcoin", "Daily fluctuations for the last year", "Hourly fluctuations for the last day", "Fluctuations from every minute during the last hour"};

        for(int i = 0; i < bitcoin_options.length; i++){
            Model model;
            model = new Model(
                    bitcoin_options[i],
                    bitcoin_descriptions[i]
            );
            listModels.add(model);
            bitcoinAdapter = new Bitcoin_adapter(listModels, this);
            bitcoinView.setAdapter(bitcoinAdapter);
        }
    }
}