package com.example.antonio.cryptracer.Currencies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.antonio.cryptracer.R;

public class Currency extends AppCompatActivity {

    private RecyclerView currencyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        final String coinName = extras.getString("coinName");
        currencyView = (RecyclerView) findViewById(R.id.currency_view);
        currencyView.setHasFixedSize(false);
        currencyView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(R.layout.activity_currency);
    }
}
