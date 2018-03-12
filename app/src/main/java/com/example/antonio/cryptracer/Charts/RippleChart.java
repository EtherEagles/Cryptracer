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
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RippleChart extends AppCompatActivity {

    private CandleStickChart candleStickChart;
    private String period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_chart);

        Bundle extra = getIntent().getExtras();
        period = extra.getString("period");

        OkHttpClient client = new OkHttpClient();
        String API_KEY = new String(getKey(period));

        Request request = new Request.Builder()
                .url(API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(RippleChart.this, "Error Loading Data" +
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

    public void buildChart(String data) {
        try {
            ArrayList<CandleEntry> entries = new ArrayList<>();
            candleStickChart = findViewById(R.id.rippleChart);

            JSONObject jsonData = new JSONObject(data);
            JSONArray candleData = jsonData.getJSONArray("Data");

            for (int i = 0; i < candleData.length(); i++) {
                JSONObject stickData = candleData.getJSONObject(i);
                float high = (float) stickData.getDouble("high");
                float low = (float) stickData.getDouble("low");
                float open = (float) stickData.getDouble("open");
                float close = (float) stickData.getDouble("close");
                entries.add(new CandleEntry(i + 1, high, low, open, close));
            }

            String labelString = createLabel(period);
            CandleDataSet dataset = new CandleDataSet(entries, labelString);

            dataset.setColor(Color.rgb(80, 80, 80));
            dataset.setShadowColor(Color.DKGRAY);
            dataset.setShadowWidth(1f);
            dataset.setDecreasingColor(Color.RED);
            dataset.setDecreasingPaintStyle(Paint.Style.FILL);
            dataset.setIncreasingColor(Color.GREEN);
            dataset.setIncreasingPaintStyle(Paint.Style.FILL);
            dataset.setNeutralColor(Color.GREEN);

            CandleData data2 = new CandleData(dataset);

            candleStickChart.getDescription().setText("Ripple");
            candleStickChart.animateX(3000);
            //candleStickChart.animateY(3000);
            candleStickChart.setData(data2);

            candleStickChart.invalidate();
        } catch (Exception e) {

        }
    }

    public String createLabel(String period){
        if(period.equals("year")){
            String labelString = "Daily fluctuations for the last 365 days";
            return labelString;
        } else if(period.equals("day")){
            String labelString = "Daily fluctuations for the last 24 hours";
            return labelString;
        } else if(period.equals("hour")){
            String labelString = "Daily fluctuations for the last 60 minutes";
            return labelString;
        } else{
            return null;
        }
    }

    public String getKey(String period){

        if(period.equals("year")) {
            String key = "https://min-api.cryptocompare.com/data/histoday?fsym=XRP&tsym=USD&limit=364&aggregate=3&e=CCCAGG";
            return key;
        } else if(period.equals("day")) {
            String key = "https://min-api.cryptocompare.com/data/histohour?fsym=XRP&tsym=USD&limit=24&aggregate=3&e=CCCAGG";
            return key;
        } else if(period.equals("hour")) {
            String key = "https://min-api.cryptocompare.com/data/histominute?fsym=XRP&tsym=USD&limit=60&aggregate=3&e=CCCAGG";
            return key;
        } else {
            return null;
        }
    }
}
