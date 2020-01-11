package com.example.antonio.cryptracer.Charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.antonio.cryptracer.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Analytics extends AppCompatActivity {

    CandleStickChart candleStickChart;
    public int[] colorList = {Color.BLUE, Color.RED, Color.BLACK, Color.GREEN, Color.GRAY};
    public String[] keyStart = {"histoday", "histohour", "histominute"};
    public String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        Bundle extras = getIntent().getExtras();
        final String coinName = extras.getString("coinName");
        final String period = extras.getString("period");
        buildMulti(coinName, period);
    }

    public void buildMulti(final String coinName, final String period) {

        String API_KEY = new String(buildKey(coinName, period));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_KEY)
                .build();

        Log.e("tag", "h1");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(Analytics.this, "Error Loading Data" +
                        e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {

                    final String body = response.body().string();

                    @Override
                    public void run() {
                        try {
                            candleStickChart = (CandleStickChart) findViewById(R.id.candlechart);
                            ArrayList<CandleEntry> entries = new ArrayList<>();
                            JSONObject jsonData = new JSONObject(body);
                            JSONArray candleData = jsonData.getJSONArray("Data");
                            for (int i = 0; i < candleData.length(); i++) {
                                JSONObject stickData = candleData.getJSONObject(i);
                                float high = (float) stickData.getDouble("high");
                                float low = (float) stickData.getDouble("low");
                                float open = (float) stickData.getDouble("open");
                                float close = (float) stickData.getDouble("close");
                                entries.add(new CandleEntry(i+1,  high, low, open, close));
                            }
                            CandleDataSet dataSet = new CandleDataSet(entries, period);
                            dataSet.setColor(Color.rgb(80, 80, 80));
                            dataSet.setShadowWidth(0.7f);
                            dataSet.setDecreasingColor(Color.RED);
                            dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
                            dataSet.setIncreasingColor(Color.GREEN);
                            dataSet.setIncreasingPaintStyle(Paint.Style.FILL);
                            dataSet.setNeutralColor(Color.GREEN);
                            dataSet.setValueTextColor(Color.BLACK);
                            CandleData finalData = new CandleData(dataSet);
                            String description = getDescription(coinName);
                            candleStickChart.getDescription().setText(description);
                            candleStickChart.animateX(3000);
                            candleStickChart.setData(finalData);
                            candleStickChart.invalidate();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
    }

    public String getDescription(String period){

        String description = new String();

        if(period.equals("Yearly")){
            description = new String("Last 365 days");
        } else if(period.equals("Daily")){
            description = new String("Last 24 hours");
        } else if(period.equals("Hourly")){
            description = new String("Last 60 minutes");
        }
        return description;
    }

    public String buildKey(String coinName, String period){
        Log.e("tag", period);
        if(period.equals("Yearly")){
            Log.e("tag", "imhere");
            key = "https://min-api.cryptocompare.com/data/"+keyStart[0]+"?fsym="+coinName+"&tsym=USD&limit=364&aggregate=3&e=CCCAGG";
        } else if(period.equals("Daily")){
            key = "https://min-api.cryptocompare.com/data/"+keyStart[1]+"?fsym="+coinName+"&tsym=USD&limit=23&aggregate=3&e=CCCAGG";
        } else if(period.equals("Hourly")){
            key = "https://min-api.cryptocompare.com/data/"+keyStart[2]+"?fsym="+coinName+"&tsym=USD&limit=59&aggregate=3&e=CCCAGG";
        }
        Log.e("tag", "imhe"+key);
        return key;
    }
}