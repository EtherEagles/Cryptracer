package com.example.antonio.cryptracer.Currencies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antonio.cryptracer.Charts.Storjcoin_Chart_Day;
import com.example.antonio.cryptracer.R;

public class Storjcoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storjcoin);
        Intent intent = new Intent(this, Storjcoin_Chart_Day.class);
        startActivity(intent);
    }
}
