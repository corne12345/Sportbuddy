package com.example.corne.sportbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        Intent oldIntent = getIntent();
        final int calories = (int) oldIntent.getSerializableExtra("calories");

        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportActivity.this, SumActivity.class);
                intent.putExtra("calories", calories);

                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int checkecButton = radioGroup.getCheckedRadioButtonId();
                RadioButton checked = findViewById(checkecButton);
                String activity = (String) checked.getText();
                intent.putExtra("activity", activity);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button6);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(SportActivity.this, DetailActivity.class);
//                intent2.putExtra("calories", calories);
//                startActivity(intent2);
//            }
//        });

    }
}
