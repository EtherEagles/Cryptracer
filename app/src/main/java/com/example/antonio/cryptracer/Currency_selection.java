package com.example.antonio.cryptracer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.antonio.cryptracer.Adapters.currency_selection_adapter;

import java.util.ArrayList;

public class Currency_selection extends AppCompatActivity {

    private static final String TAG = "Currency_selection";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selection);
        initImageBitmaps();
    }

    private void initImageBitmaps(){

        mImageUrls.add("https://bitcoin.org/img/icons/opengraph.png");
        mNames.add("Bitcoin");

        mImageUrls.add("http://neocashradio.com/wp-content/uploads/2016/11/ethereum-cryptocurrency-ether-ETH.png");
        mNames.add("Ether");

        mImageUrls.add("http://wpmedia.o.canada.com/2014/02/litecoin-logo.jpg?w=300");
        mNames.add("Litecoin");

        mImageUrls.add("https://ih0.redbubble.net/image.345329668.3028/sticker,375x360-bg,ffffff.u3.png");
        mNames.add("Monero");

        mImageUrls.add("https://i.ebayimg.com/images/g/8EwAAOSwvD5aSL8v/s-l300.jpg");
        mNames.add("Ripple");

        mImageUrls.add("https://pbs.twimg.com/profile_images/471272699713421312/HCd40Yan_400x400.png");
        mNames.add("Dogecoin");

        mImageUrls.add("https://bittox.com/wp-content/uploads/2017/11/DASH-Keychain-5.png");
        mNames.add("Dash");

        mImageUrls.add("https://pbs.twimg.com/profile_images/925701307561840641/8LzIcBXt_400x400.jpg");
        mNames.add("MaidSafeCoin");

        mImageUrls.add("https://3mgj4y44nc15fnv8d303d8zb-wpengine.netdna-ssl.com/wp-content/uploads/2017/11/lisk-696x449.jpg");
        mNames.add("Lisk");

        mImageUrls.add("https://pbs.twimg.com/profile_images/835199495013019648/cNeEcEDW.jpg");
        mNames.add("Storjcoin");

        mImageUrls.add("https://raw.githubusercontent.com/rcr095/rcr095/master/graph.png");
        mNames.add("Multichart");

        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        currency_selection_adapter adapter = new currency_selection_adapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
