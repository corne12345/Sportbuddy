package com.example.corne.sportbuddy;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity implements FoodRequest.Callback {
    String input;
    float totalCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Intent gotIntent = getIntent();
        if (gotIntent.hasExtra("calories")){
            totalCalories = (float) gotIntent.getSerializableExtra("calories");
        } else {
            totalCalories = (float) 0.0;
        }
        Button button = findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView = findViewById(R.id.editText);
                input = textView.getText().toString();
                Log.e("Developer", input);
                FoodRequest x = new FoodRequest(InputActivity.this, input);
                x.getFood((FoodRequest.Callback) InputActivity.this);

                RadioGroup mRadioGroup = findViewById(R.id.RadioGroup);
                mRadioGroup.setVisibility(View.VISIBLE);
            }
        });
        Button button2 = findViewById(R.id.button8);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup mRadioGroup = findViewById(R.id.RadioGroup);
                int choice = mRadioGroup.getCheckedRadioButtonId();
                RadioButton choiceButton = findViewById(choice);
                String choiceString = (String) choiceButton.getText();
                Intent intent = new Intent(InputActivity.this, ViewActivity.class);;
                intent.putExtra("choice", choiceString);
                intent.putExtra("calories", totalCalories);
                startActivity(intent);
            }
        });
    }

    @Override
    public void gotFood(final ArrayList<String> food){
        String test1 = food.get(0);
        String test2 = food.get(1);
        String test3 = food.get(2);
        String test4 = food.get(3);
        String test5 = food.get(4);
        Log.e("Developer", test1);
        Log.e("Developer", test2);
        Log.e("Developer", test3);
        Log.e("Developer", test4);
        Log.e("Developer", test5);

        RadioButton option1 = findViewById(R.id.option1);
        option1.setText(test1);
        RadioButton option2 = findViewById(R.id.option2);
        option2.setText(test2);
        RadioButton option3 = findViewById(R.id.option3);
        option3.setText(test3);
        RadioButton option4 = findViewById(R.id.option4);
        option4.setText(test4);
        RadioButton option5 = findViewById(R.id.option5);
        option5.setText(test5);
    }

    @Override
    public void gotFoodError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e("Developer", "Failed request");
    }


}
