package com.example.antonio.cryptracer.Price;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antonio.cryptracer.Currency_selection;
import com.example.antonio.cryptracer.Intro;
import com.example.antonio.cryptracer.R;

import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StorjcoinPrice extends AppCompatActivity {

    String[] text = {"Load"};
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storjcoin_price);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("BPI Loading");
        progressDialog.setMessage("Wait...");
    }

    public void startLoad(View view){
        try {
            Request request = new Request.Builder()
                    .url("https://min-api.cryptocompare.com/data/price?fsym=SJCX&tsyms=USD,GBP,EUR")
                    .build();

            progressDialog.show();
            OkHttpClient okHttpClient = new OkHttpClient();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(StorjcoinPrice.this, "Error during BPI loading: " +
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String body = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            load(body);
                        }
                    });
                }
            });
        } catch (Exception e){

        }
    }

    private void load(String body){
        try {
            TextView txtView = findViewById(R.id.txt);
            StringBuilder builder = new StringBuilder();
            JSONObject jsonObject = new JSONObject(body);

            builder.append(jsonObject.getString("USD")).append(" $").append("\n");
            builder.append(jsonObject.getString("GBP")).append(" £").append("\n");
            builder.append(jsonObject.getString("EUR")).append(" €").append("\n");

            txtView.setText(builder.toString());
        } catch (Exception e){

        }
    }
}
