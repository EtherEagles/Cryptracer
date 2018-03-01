package com.example.antonio.cryptracer.Charts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.antonio.cryptracer.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Multichart extends AppCompatActivity {

    LineChart lineChart;
    public int[] colorList = {Color.argb(255, 0, 0, 255),
            Color.argb(255, 255, 0, 0),
            Color.argb(255, 0, 255, 0),
            Color.argb(255, 145, 145, 145),
            Color.argb(255, 255, 105, 4),
            Color.argb(255, 1, 69, 4),
            Color.argb(255, 1, 212, 70),
            Color.argb(255, 1, 83, 136),
            Color.argb(255, 0, 0, 0),
            Color.argb(255, 91, 29, 53)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multichart);
        Bundle extras = getIntent().getExtras();
        List<String> loadData = extras.getStringArrayList("loadSelection");
        buildMulti(loadData);
    }

    public void buildMulti(List<String> loadData) {

        final ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        for (int i = 0; i < loadData.size(); i++) {
            final int iName = i;
            final String tagName = loadData.get(i);
            String API_KEY = buildKey(tagName, 30);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_KEY)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(Multichart.this, "Error Loading Data" +
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    runOnUiThread(new Runnable() {

                        final String body = response.body().string();

                        @Override
                        public void run() {
                            try {
                                ArrayList<Entry> entries = new ArrayList<>();
                                lineChart =findViewById(R.id.lineChart);
                                JSONObject jsonData = new JSONObject(body);
                                JSONArray lineData = jsonData.getJSONArray("Data");

                                for (int x = 0; x < lineData.length(); x++) {
                                    JSONObject stickData = lineData.getJSONObject(x);
                                    float close = (float) stickData.getDouble("close");
                                    entries.add(new Entry(x, close));
                                }
                                LineDataSet tempSet = new LineDataSet(entries, tagName);
                                tempSet.setDrawCircles(false);

                                tempSet.setColor(colorList[iName]);
                                lineDataSets.add(tempSet);
                                lineChart.getDescription().setText(" ");
                                lineChart.animateY(5000);
                                lineChart.animateX(5000);
                                lineChart.setData(new LineData(lineDataSets));
                                lineChart.invalidate();
                            } catch (Exception e) {

                            }
                        }
                    });
                }
            });
        }
    }

    public String buildKey(String tag, int time){
        String key;
        key = "https://min-api.cryptocompare.com/data/histoday?fsym="+tag+"&tsym=USD&limit="+String.valueOf(time)+"&aggregate=3&e=CCCAGG";
        return key;
    }
}