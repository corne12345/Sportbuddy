package com.example.corne.sportbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        Intent oldIntent = getIntent();
        final int calories = (int) oldIntent.getSerializableExtra("calories");
        final String activity = (String) oldIntent.getSerializableExtra("activity");

        Button button2 = findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (SumActivity.this, SportActivity.class);
                intent2.putExtra("calories", calories);
                startActivity(intent2);
            }
        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SumActivity.this, MapsActivity.class);
                intent.putExtra("calories", calories);
                intent.putExtra("activity", activity);
                startActivity(intent);
            }
        });

    }
}
