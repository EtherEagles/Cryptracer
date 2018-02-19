package com.example.antonio.cryptracer.Charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.antonio.cryptracer.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Storjcoin_Chart_Day extends AppCompatActivity {

    private CandleStickChart candleStickChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storjcoin__chart__day);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://min-api.cryptocompare.com/data/histoday?fsym=SJCX&tsym=USD&limit=364&aggregate=3&e=CCCAGG")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(Storjcoin_Chart_Day.this, "Error Loading Data" +
                        e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {

                    final String body = response.body().string();

                    @Override
                    public void run() {
                        buildChart(body);
                    }
                });
            }
        });
    }

    private void buildChart(String data) {
        try {
            ArrayList<CandleEntry> entries = new ArrayList<>();
            candleStickChart = findViewById(R.id.chart);
            JSONObject jsonData = new JSONObject(data);
            JSONArray candleData = jsonData.getJSONArray("Data");
            for(int i = 0; i < candleData.length(); i++){
                JSONObject stickData = candleData.getJSONObject(i);
                float high =  (float) stickData.getDouble("high");
                float low =  (float) stickData.getDouble("low");
                float open = (float) stickData.getDouble("open");
                float close = (float) stickData.getDouble("close");
                entries.add(new CandleEntry(i+1, high, low, open, close));
            }
            CandleDataSet dataset = new CandleDataSet(entries, "Daily fluctuations for the last 365 days");
            dataset.setColor(Color.rgb(80, 80, 80));
            dataset.setShadowColor(Color.DKGRAY);
            dataset.setShadowWidth(1f);
            dataset.setDecreasingColor(Color.RED);
            dataset.setDecreasingPaintStyle(Paint.Style.FILL);
            dataset.setIncreasingColor(Color.GREEN);
            dataset.setIncreasingPaintStyle(Paint.Style.FILL);
            dataset.setNeutralColor(Color.GREEN);
            CandleData data2 = new CandleData(dataset);
            candleStickChart.getDescription().setText("Storjcoin");
            candleStickChart.animateY(5000);
            candleStickChart.animateX(5000);
            candleStickChart.setData(data2);
            candleStickChart.invalidate();
        } catch (Exception e) {

        }
    }
}
