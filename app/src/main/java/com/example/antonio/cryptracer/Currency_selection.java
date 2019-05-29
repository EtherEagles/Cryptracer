package com.example.antonio.cryptracer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.antonio.cryptracer.Adapters.currency_selection_adapter;

import java.util.ArrayList;

public class Currency_selection extends AppCompatActivity {

    private static final String TAG = "Currency_selection";

    private ArrayList<String> coinNames = new ArrayList<>();
    private ArrayList<String> coinImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selection);
        initImageBitmaps();
    }

    private void initImageBitmaps(){

        coinImages.add("https://bitcoin.org/img/icons/opengraph.png");
        coinNames.add("Bitcoin");

        coinImages.add("http://neocashradio.com/wp-content/uploads/2016/11/ethereum-cryptocurrency-ether-ETH.png");
        coinNames.add("Ether");

        coinImages.add("http://wpmedia.o.canada.com/2014/02/litecoin-logo.jpg?w=300");
        coinNames.add("Litecoin");

        coinImages.add("https://ih0.redbubble.net/image.345329668.3028/sticker,375x360-bg,ffffff.u3.png");
        coinNames.add("Monero");

        coinImages.add("https://i.ebayimg.com/images/g/8EwAAOSwvD5aSL8v/s-l300.jpg");
        coinNames.add("Ripple");

        coinImages.add("https://pbs.twimg.com/profile_images/471272699713421312/HCd40Yan_400x400.png");
        coinNames.add("Dogecoin");

        coinImages.add("https://bittox.com/wp-content/uploads/2017/11/DASH-Keychain-5.png");
        coinNames.add("Dash");

        coinImages.add("https://pbs.twimg.com/profile_images/925701307561840641/8LzIcBXt_400x400.jpg");
        coinNames.add("MaidSafeCoin");

        coinImages.add("https://3mgj4y44nc15fnv8d303d8zb-wpengine.netdna-ssl.com/wp-content/uploads/2017/11/lisk-696x449.jpg");
        coinNames.add("Lisk");

        coinImages.add("https://pbs.twimg.com/profile_images/835199495013019648/cNeEcEDW.jpg");
        coinNames.add("Storjcoin");

        coinImages.add("https://raw.githubusercontent.com/rcr095/rcr095/master/graph.png");
        coinNames.add("Multichart");

        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        currency_selection_adapter adapter = new currency_selection_adapter(this, coinNames, coinImages);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
