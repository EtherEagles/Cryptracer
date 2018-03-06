package com.example.antonio.cryptracer.Currencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.antonio.cryptracer.Charts.StorjcoinChart;
import com.example.antonio.cryptracer.Didactic_units.StorjcoinDidactic;
import com.example.antonio.cryptracer.Price.StorjcoinPrice;
import com.example.antonio.cryptracer.R;

public class Storjcoin extends AppCompatActivity {

    String [] chartOptions = {"About", "Price", "Year Chart", "Daily Chart", "Hour Chart"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storjcoin);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, chartOptions);
        final ListView lv = findViewById(R.id.list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(chartOptions[position] == "About"){
                    Intent intent = new Intent(Storjcoin.this, StorjcoinDidactic.class);
                    startActivity(intent);
                } else if(chartOptions[position] == "Price"){
                    Intent intent = new Intent(Storjcoin.this, StorjcoinPrice.class);
                    startActivity(intent);
                } else if(chartOptions[position] == "Year Chart"){
                    Intent intent = new Intent(Storjcoin.this, StorjcoinChart.class);
                    intent.putExtra("period","year");
                    startActivity(intent);
                } else if(chartOptions[position] == "Daily Chart"){
                    Intent intent = new Intent(Storjcoin.this, StorjcoinChart.class);
                    intent.putExtra("period","day");
                    startActivity(intent);
                } else if(chartOptions[position] == "Hour Chart"){
                    Intent intent = new Intent(Storjcoin.this, StorjcoinChart.class);
                    intent.putExtra("period","hour");
                    startActivity(intent);
                }
            }
        });
    }
}
