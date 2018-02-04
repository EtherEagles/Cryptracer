package com.example.antonio.cryptracer.Charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.antonio.cryptracer.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;

public class Bitcoin_Chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin_chart);
        CandleStickChart candleStickChart = (CandleStickChart) findViewById(R.id.chart);
        ArrayList<CandleEntry> entries = new ArrayList<>();
        entries.add(new CandleEntry(0, 4.62f, 2.02f, 2.70f, 4.13f));
        entries.add(new CandleEntry(1, 5.50f, 2.70f, 3.35f, 4.96f));
        entries.add(new CandleEntry(2, 5.25f, 3.02f, 3.50f, 4.50f));
        entries.add(new CandleEntry(3, 6f,    3.25f, 4.40f, 5.0f));
        entries.add(new CandleEntry(4, 5.57f, 2f,    2.80f, 4.5f));
        CandleDataSet cds = new CandleDataSet(entries, "Entries");
        cds.setColor(Color.rgb(80, 80, 80));
        cds.setShadowColor(Color.DKGRAY);
        cds.setShadowWidth(0.7f);
        cds.setDecreasingColor(Color.DKGRAY);
        cds.setDecreasingPaintStyle(Paint.Style.FILL);
        cds.setIncreasingColor(Color.BLUE);
        cds.setIncreasingPaintStyle(Paint.Style.FILL);
        cds.setNeutralColor(Color.BLUE);
        cds.setValueTextColor(Color.BLACK);
        CandleData cd = new CandleData(cds);
        candleStickChart.setData(cd);
        candleStickChart.invalidate();
    }
}
