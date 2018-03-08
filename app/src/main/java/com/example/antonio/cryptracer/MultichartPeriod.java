package com.example.antonio.cryptracer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.antonio.cryptracer.Charts.Multichart;
import com.example.antonio.cryptracer.Currencies.MultichartSelection;
import com.example.antonio.cryptracer.R;

public class MultichartPeriod extends AppCompatActivity {

    String [] optionList = {"Last 365 days", "Last 30 days",
                            "Last 7 days", "Last 24 hours",
                            "Last 60 minutes"};
    String[] periodList = {"Yearly", "Monthly", "Weekly", "Daily", "Hourly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multichart_period);

        TextView textView = findViewById(R.id.multiTxt);
        textView.setText("Select Time Period");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, optionList);
        final ListView lv = findViewById(R.id.multiList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selected = periodList[position];
                Intent intent = new Intent(MultichartPeriod.this, MultichartSelection.class);
                intent.putExtra("loadPeriod", selected);
                startActivity(intent);
            }
        });
    }
}
