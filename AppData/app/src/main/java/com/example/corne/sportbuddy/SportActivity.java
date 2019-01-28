package com.example.corne.sportbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        Intent oldIntent = getIntent();
        final float calories = (float) oldIntent.getSerializableExtra("calories");
        Log.e("Developer", String.valueOf(calories));

        TextView textView = findViewById(R.id.textView2);
        textView.setText("You are to lose " + String.valueOf(calories) + " calories" );

        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportActivity.this, SumActivity.class);


                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int checkecButton = radioGroup.getCheckedRadioButtonId();
                RadioButton checked = findViewById(checkecButton);

                float MET = 0;
                String activity = (String) checked.getText();
                if (activity.equals("Walk")){
                    MET = (float) 3.3;
                } else if (activity.equals ("Run")) {
                    MET = (float) 7.0;
                } else if (activity.equals("Cycle")){
                    MET = (float) 5.5;
                } else if (activity.equals("Swim")) {
                    MET = (float) 8.0;
                } else if (activity.equals("Row")) {
                    MET = (float) 7.0;
                } else if (activity.equals("Do Calisthenics")){
                    MET = (float) 8.0;
                } else{
                    MET = (float) 1.0;
                }

                float duration = (float) (calories / (MET * 1.5));

                intent.putExtra("duration", duration);
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
