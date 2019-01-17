package com.example.corne.sportbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity implements FoodRequest.Callback {
    String input = "cake";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        FoodRequest x = new FoodRequest(InputActivity.this, input);
        x.getFood((FoodRequest.Callback) InputActivity.this);

        Button button = findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView = findViewById(R.id.editText);
                input = textView.getText().toString();
//                Log.e("Developer", input);

//                FoodRequest x = new FoodRequest(InputActivity.this, input);
//                x.getFood((FoodRequest.Callback) InputActivity.this);

                Intent intent = new Intent(InputActivity.this, ViewActivity.class);
                intent.putExtra("Input", input);
                startActivity(intent);
            }
        });

    }

    @Override
    public void gotFood(final ArrayList<String> food){
        String test = food.get(1);
        String test2 = food.get(2);
        Log.e("Developer", test);
        Log.e("Developer", test2);
    }

    @Override
    public void gotFoodError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
