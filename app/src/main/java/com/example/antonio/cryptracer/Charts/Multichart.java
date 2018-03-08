package com.example.antonio.cryptracer.Charts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    public int[] colorList = {Color.BLUE, Color.RED, Color.BLACK, Color.GREEN, Color.GRAY};
    public String[] keyStart = {"histoday", "histohour", "histominute"};
    public String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multichart);

        Bundle extras = getIntent().getExtras();
        List<String> loadData = extras.getStringArrayList("loadSelection");
        String period = extras.getString("loadPeriod");
        buildMulti(loadData, period);
    }

    public void buildMulti(List<String> loadData, final String period) {

        final ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        for (int i = 0; i < loadData.size(); i++) {
            final int iName = i;
            final String tagName = loadData.get(i);
            String API_KEY = buildKey(tagName, period);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_KEY)
                    .build();

            Log.e("tag", "h1");
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
                                    entries.add(new Entry(x+1, close));
                                }
                                LineDataSet tempSet = new LineDataSet(entries, tagName);
                                tempSet.setDrawCircles(false);
                                tempSet.setColor(colorList[iName]);
                                lineDataSets.add(tempSet);
                                String description = getDescription(period);
                                lineChart.getDescription().setText(description);
                                if(lineData.length() > 20) {
                                    //lineChart.animateY(3000);
                                    lineChart.animateX(3000);
                                } else {
                                    lineChart.animateX(2000);
                                }
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

    public String getDescription(String period){

        String description = new String();

        if(period.equals("Yearly")){
            description = new String("Last 365 days");
        } else if(period.equals("Monthly")){
            description = new String("Last 30 days");
        } else if(period.equals("Weekly")){
            description = new String("Last 7 days");
        } else if(period.equals("Daily")){
            description = new String("Last 24 hours");
        } else if(period.equals("Hourly")){
            description = new String("Last 60 minutes");
        }
        return description;
    }

    public String buildKey(String tag, String period){
        Log.e("tag", period);
        if(period.equals("Yearly")){
            Log.e("tag", "imhere");
            key = "https://min-api.cryptocompare.com/data/"+keyStart[0]+"?fsym="+tag+"&tsym=USD&limit=364&aggregate=3&e=CCCAGG";
        } else if(period.equals("Monthly")){
            key = "https://min-api.cryptocompare.com/data/"+keyStart[0]+"?fsym="+tag+"&tsym=USD&limit=29&aggregate=3&e=CCCAGG";
        } else if(period.equals("Weekly")){
            key = "https://min-api.cryptocompare.com/data/"+keyStart[0]+"?fsym="+tag+"&tsym=USD&limit=6&aggregate=3&e=CCCAGG";
        } else if(period.equals("Daily")){
            key = "https://min-api.cryptocompare.com/data/"+keyStart[1]+"?fsym="+tag+"&tsym=USD&limit=23&aggregate=3&e=CCCAGG";
        } else if(period.equals("Hourly")){
            key = "https://min-api.cryptocompare.com/data/"+keyStart[2]+"?fsym="+tag+"&tsym=USD&limit=59&aggregate=3&e=CCCAGG";
        }
        Log.e("tag", "imhe"+key);
        return key;
    }
}