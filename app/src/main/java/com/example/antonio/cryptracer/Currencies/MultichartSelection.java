package com.example.antonio.cryptracer.Currencies;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.antonio.cryptracer.Charts.Multichart;
import com.example.antonio.cryptracer.R;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLACK;


public class MultichartSelection extends AppCompatActivity {

    String [] coinList = {"Bitcoin","Dash","Dogecoin","Ether","Lisk","Litecoin","MaidSafeCoin","Monero","Ripple","Storjcoin"};
    String [] coinTag = {"BTC", "DSH", "DOGE", "ETH", "LSK", "LTC", "MAID", "XMR", "XRP", "SJCX"};
    public ArrayList<String> selected = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multichart_selection);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, coinList);
        final ListView lv = findViewById(R.id.multiSelList);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String current = coinTag[position];
                if(!selected.contains(current)){
                    if(selected.size()<5){
                        selected.add(current);
                        if(selected.size() == 5){
                            lv.setChoiceMode(ListView.CHOICE_MODE_NONE);
                        }
                    } else {
                        Toast.makeText(MultichartSelection.this, "Limit of 5 currencies!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    if(selected.size()<5){
                        selected.remove(current);
                    } else{
                        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                        lv.setItemChecked(position, false);
                        selected.remove(current);
                    }
                }
            }

        });
    }

    public void loadMulti(View view){

        if(selected.size()>0) {
            Bundle periodExtra = getIntent().getExtras();
            String period = periodExtra.getString("loadPeriod");

            Intent intent = new Intent(this, Multichart.class);
            intent.putStringArrayListExtra("loadSelection", selected);
            intent.putExtra("loadPeriod", period);
            startActivity(intent);
        } else{
            Toast.makeText(MultichartSelection.this, "Select a currency!", Toast.LENGTH_SHORT).show();
        }
    }
}

