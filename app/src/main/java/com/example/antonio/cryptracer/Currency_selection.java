package com.example.antonio.cryptracer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.antonio.cryptracer.Adapters.Currency_selection_adapter;
import com.example.antonio.cryptracer.Currencies.Currency;

import java.util.ArrayList;
import java.util.Arrays;

public class Currency_selection extends AppCompatActivity {

    private ArrayList<String> coinNames = new ArrayList<>(Arrays.asList("BTC", "ETH", "LTC", "XMR", "XRP", "DOGE", "DSH", "MAID", "LSK", "SJCX"));
    private ArrayList<String> coinImages = new ArrayList<>(Arrays.asList("https://bitcoin.org/img/icons/opengraph.png",
            "http://neocashradio.com/wp-content/uploads/2016/11/ethereum-cryptocurrency-ether-ETH.png",
            "http://wpmedia.o.canada.com/2014/02/litecoin-logo.jpg?w=300",
            "https://ih0.redbubble.net/image.345329668.3028/sticker,375x360-bg,ffffff.u3.png",
            "https://themerkle.com/wp-content/uploads/2015/12/Ripple-XRP.png",
            "https://pbs.twimg.com/profile_images/471272699713421312/HCd40Yan_400x400.png",
            "https://bittox.com/wp-content/uploads/2017/11/DASH-Keychain-5.png",
            "https://pbs.twimg.com/profile_images/925701307561840641/8LzIcBXt_400x400.jpg",
            "https://3mgj4y44nc15fnv8d303d8zb-wpengine.netdna-ssl.com/wp-content/uploads/2017/11/lisk-696x449.jpg",
            "https://pbs.twimg.com/profile_images/835199495013019648/cNeEcEDW.jpg"
            ));
    private RecyclerView selectionView;
    private RecyclerView.Adapter selectionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_recyclerview);
        selectionView = findViewById(R.id.recycler_view);
        selectionView.setHasFixedSize(false);
        selectionView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        selectionView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        selectionView.setItemAnimator(new DefaultItemAnimator());
        selectionAdapter = new Currency_selection_adapter(this, coinNames, coinImages);
        selectionView.setAdapter(selectionAdapter);
        selectionView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), selectionView, new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(), coinNames.get(position), Toast.LENGTH_SHORT).show();

                Intent currency = new Intent(getApplicationContext(), Currency.class);
                newActivity(currency, coinNames.get(position), coinImages.get(position));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
    public void newActivity(Intent currency, final String coinName, final String coinImage){
        currency.putExtra("coinName", coinName);
        currency.putExtra("coinImage", coinImage);
        getApplicationContext().startActivity(currency);
    }
}
