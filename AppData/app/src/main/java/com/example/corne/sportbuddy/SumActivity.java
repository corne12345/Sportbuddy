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

        // Get the intent from the previous activities to do the calculations with
        Intent oldIntent = getIntent();
        final float durationFloat = (float) oldIntent.getSerializableExtra("duration");
        final int duration = Math.round(durationFloat);
        final String activity = (String) oldIntent.getSerializableExtra("activity");
        final int calories = (int) oldIntent.getSerializableExtra("calories");

        // Set text with the information obtained from the intent
        TextView textView = findViewById(R.id.textView5);
        textView.setText("You have to " + activity + " for " + String.valueOf(duration) + " minutes to compensate. I will help you to achieve this.");

        // OnClickListeners for both the edit and the start button, which leads to new activities
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
                intent.putExtra("duration", duration);
                intent.putExtra("activity", activity);
                intent.putExtra("calories", calories);
                startActivity(intent);
            }
        });
    }
}
