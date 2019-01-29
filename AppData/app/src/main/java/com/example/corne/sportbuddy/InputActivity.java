package com.example.corne.sportbuddy;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
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

        // Load calories if this activity is loaded for a second time
        if (gotIntent.hasExtra("calories")){
            totalCalories = (float) gotIntent.getSerializableExtra("calories");

        // Set totalCalories to 0 if Activity is opened for the first time
        } else {
            totalCalories = (float) 0.0;
        }

        // OnClickListener for searchButton
        Button button = findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Take editText as input and start request with this input
                EditText textView = findViewById(R.id.editText);
                input = textView.getText().toString();

                // Give error message if no input is provided
                if(input.length() == 0){
                    Toast.makeText(InputActivity.this, "No search query provided", Toast.LENGTH_SHORT).show();
                }
                FoodRequest search = new FoodRequest(InputActivity.this, input);
                search.getFood( InputActivity.this);
            }
        });
    }

    // Result method for successful Foodrequest
    @Override
    public void gotFood(final ArrayList<String> food, final ArrayList<String> servingUnit){

        // Show toast if no result were found and display empty list
        if (food.size() < 1){
            Toast.makeText(this, "No search results", Toast.LENGTH_LONG).show();
        }

        // Create foodsAdapter consisting of the foods and serving sizes and set them in listview
        FoodsAdapter foodsAdapter = new FoodsAdapter(this, R.layout.linear_item, food, servingUnit);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(foodsAdapter);

        // Create onItemClickListener, which makes an intent with the choice as an extra
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choiceString = (String) parent.getItemAtPosition(position);
                Log.e("Developer", choiceString);
                Intent intent = new Intent(InputActivity.this, ViewActivity.class);;
                intent.putExtra("choice", choiceString);
                intent.putExtra("calories", totalCalories);
                startActivity(intent);
            }
        });
    }

    // Result method for failed Foodrequest shows a toast warning for the failure
    @Override
    public void gotFoodError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
