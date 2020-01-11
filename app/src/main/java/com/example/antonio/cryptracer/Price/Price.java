package com.example.antonio.cryptracer.Price;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.antonio.cryptracer.R;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Price extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private String coinImage;
    private String coinName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        ImageView logo = (ImageView) findViewById(R.id.logo);
        Bundle extras = getIntent().getExtras();
        coinImage = extras.getString("coinImage");
        coinName = extras.getString("coinName");
        Glide.with(this)
                .asBitmap()
                .load(coinImage)
                .into(logo);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("BPI Loading");
        progressDialog.setMessage("Wait...");
    }

    public void loadPrice(View view){
        try {
            Request request = new Request.Builder()
                    .url("https://min-api.cryptocompare.com/data/price?fsym="+coinName+"&tsyms=USD,GBP,EUR")
                    .build();

            progressDialog.show();
            OkHttpClient okHttpClient = new OkHttpClient();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(Price.this, "Error during BPI loading: " +
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String body = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            display(body);
                        }
                    });
                }
            });
        } catch (Exception e){

        }
    }
    public void display(String body){
        try {
            TextView price = findViewById(R.id.Txt);
            StringBuilder builder = new StringBuilder();

            JSONObject jsonObject = new JSONObject(body);

            builder.append(jsonObject.getString("USD")).append(" $").append("\n");
            builder.append(jsonObject.getString("GBP")).append(" £").append("\n");
            builder.append(jsonObject.getString("EUR")).append(" €").append("\n");

            price.setText(builder.toString());

        } catch (Exception e){

        }
    }

}
