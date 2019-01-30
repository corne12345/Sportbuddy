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

        // Retrieve the amount of calaries from previous activities and put it in textview
        Intent oldIntent = getIntent();
        final int calories = (int) oldIntent.getSerializableExtra("calories");
        TextView textView = findViewById(R.id.textView2);
        textView.setText("You need te compensate " + String.valueOf(calories) + " calories." );
0
        // Set onClickListener for the backButton to create new intent
        Button backButton = findViewById(R.id.button6);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(SportActivity.this, InputActivity.class);
                intentBack.putExtra("calories", (float) calories);
                startActivity(intentBack);
            }
        });

        // Set onClickListener for the nextButton to create new intent
        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SportActivity.this, SumActivity.class);

                // Retrieve checked radioButton
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int checkecButton = radioGroup.getCheckedRadioButtonId();
                RadioButton checked = findViewById(checkecButton);

                // Use the checked button to calculate the duration with and put this in intent
                float MET = 0; //MET = Metabolic Equivalent of Task
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

                // Formula approximately calculate the duration on an activity based on calories
                float duration = (float) (calories / (MET * 1.5));

                intent.putExtra("duration", duration);
                intent.putExtra("activity", activity);
                intent.putExtra("calories", calories);
                startActivity(intent);
            }
        });
    }
}
