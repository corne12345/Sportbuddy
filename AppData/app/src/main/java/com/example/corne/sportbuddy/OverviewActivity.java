package com.example.corne.sportbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Intent gotIntent = getIntent();
        float duration = (float) gotIntent.getSerializableExtra("Duration");
        TextView durationView = findViewById(R.id.textView30);
        durationView.setText(String.valueOf(duration/1000) + " s");

        int distance = (int) gotIntent.getSerializableExtra("Distance");
        TextView distanceView = findViewById(R.id.textView32);
        distanceView.setText(String.valueOf(distance) + " m");

    }
}
