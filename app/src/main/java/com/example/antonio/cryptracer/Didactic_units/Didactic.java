package com.example.antonio.cryptracer.Didactic_units;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.example.antonio.cryptracer.R;

public class Didactic extends AppCompatActivity {

    private TextView explanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_didactic);
        explanation = (TextView) findViewById(R.id.explanation);
    }

}
