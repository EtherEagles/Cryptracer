package com.example.antonio.cryptracer.Price;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.antonio.cryptracer.R;

public class Bitcoin_price extends AppCompatActivity {

    public static final String BPI_ENDPOINT = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,GBP,EUR";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ProgressDialog progressDialog;
    private TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin_analytics);
        txt = (TextView) findViewById(R.id.txt);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("BPI Loading");
        progressDialog.setMessage("Wait...");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_load){
            load();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bitcoin_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void load(){
        Request request = new Request.Builder()
                .url(BPI_ENDPOINT)
                .build();

        progressDialog.show();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(Bitcoin_price.this, "Error during BPI loading: " +
                e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        parseBpiResponse(body);
                    }
                });
            }
        });
    }

    private void parseBpiResponse(String body) {
        try {
            StringBuilder builder = new StringBuilder();

            JSONObject jsonObject = new JSONObject(body);

            builder.append(jsonObject.getString("USD")).append(" $").append("\n");
            builder.append(jsonObject.getString("GBP")).append(" £").append("\n");
            builder.append(jsonObject.getString("EUR")).append(" €").append("\n");

            txt.setText(builder.toString());

        } catch (Exception e){

        }
    }
}

