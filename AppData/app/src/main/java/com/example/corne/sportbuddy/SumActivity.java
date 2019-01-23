package com.example.corne.sportbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        Intent oldIntent = getIntent();
        final float duration = (float) oldIntent.getSerializableExtra("duration");
        final String activity = (String) oldIntent.getSerializableExtra("activity");

        TextView textView = findViewById(R.id.textView5);
        textView.setText("You have to " + activity + " for " + String.valueOf(duration) + " minutes to compensate. I will help you to achieve this");

        Button button2 = findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (SumActivity.this, SportActivity.class);
                intent2.putExtra("calories", duration);
                startActivity(intent2);
            }
        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SumActivity.this, MapsActivity.class);
                intent.putExtra("calories", duration);
                intent.putExtra("activity", activity);
                startActivity(intent);
            }
        });

    }
}
