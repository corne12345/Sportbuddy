package com.example.corne.sportbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewActivity extends AppCompatActivity implements NutrientsRequest.Callback{

    String calories, fat, carbohydrates, protein, sodium, servingSize, servingWeight;
    float totalCalories;

    // Set servings as standard to 1 in case user doesn't give an input
    int servings = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final EditText amountServings = findViewById(R.id.editText3);

        // Get information from intent and load this for current activity and set food name as text
        Intent gotIntent = getIntent();
        totalCalories = (float) gotIntent.getSerializableExtra("calories");
        String food = gotIntent.getSerializableExtra("choice").toString();
        TextView textView = findViewById(R.id.textView18);
        textView.setText(food);

        // Make a request for nutritional information
        NutrientsRequest x = new NutrientsRequest(this, food);
        x.getNutrients( ViewActivity.this);

        // Set onClickListener for Back Button to go back to InputActivity
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewActivity.this, InputActivity.class);
                startActivity(intent1);
            }
        });

        // Set onClickListener for Add Button to go back to InputActivity saving total calories
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if servings is filled in and couple its value to servings
                String servingsString = amountServings.getText().toString();
                if (servingsString.length() > 0){
                    servings = Integer.valueOf(servingsString);
                }

                // Add food to totalcalories and put this as extra
                totalCalories += Float.valueOf(calories) * servings;
                Intent intent2 = new Intent(ViewActivity.this, InputActivity.class);
                intent2.putExtra("calories", totalCalories);
                startActivity(intent2);
            }
        });

        // Set onClickListener for Next Button to go to SportActivity
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check if sservings is filled in and couple its value to serving
                String servingsString = (String) amountServings.getText().toString();
                if (servingsString.length() > 0){
                    servings = Integer.valueOf(servingsString);
                }

                // Add food to totalcalories and put this as extra
                totalCalories += Float.valueOf(calories) * servings;
                Intent intent3 = new Intent (ViewActivity.this, SportActivity.class);
                intent3.putExtra("calories", Math.round(totalCalories));
                startActivity(intent3);
            }
        });
    }

    // Method to start after a successful request for nutrients of a chosen food
    @Override
    public void gotNutrients(final JSONObject responseObject){

        // Define all the textfields to be filled in by nutrient information
        final TextView caloriesView = findViewById(R.id.textView9);
        final TextView fatView = findViewById(R.id.textView11);
        final TextView carbohydratesView = findViewById(R.id.textView13);
        final TextView proteinView = findViewById(R.id.textView15);
        final TextView sodiumView = findViewById(R.id.textView17);
        final TextView servingSizeView = findViewById(R.id.textView20);
        final TextView servingWeightView = findViewById(R.id.textView22);

        // Assign the requested values to its defined textfields
        try{
            calories = responseObject.getString("nf_calories");
            caloriesView.setText(calories + " KCal");
            fat = responseObject.getString("nf_total_fat");
            fatView.setText(fat + " g");
            carbohydrates = responseObject.getString("nf_total_carbohydrate");
            carbohydratesView.setText(carbohydrates + " g");
            protein = responseObject.getString("nf_protein");
            proteinView.setText(protein + " g");
            sodium = responseObject.getString("nf_sodium");
            sodiumView.setText(sodium + " mg");
            servingSize = responseObject.getString("serving_unit");
            servingSizeView.setText(servingSize);
            servingWeight = responseObject.getString("serving_weight_grams");
            servingWeightView.setText(servingWeight + " g");


            // Use picasso to convert the image link to the picture and display this picture
            JSONObject photoArray = responseObject.getJSONObject("photo");
            String urlPhoto = photoArray.getString("highres");
            Log.e("Developer", urlPhoto);
            ImageView photo = findViewById(R.id.imageView2);
            Picasso.get().load(urlPhoto).resize(600, 600).centerInside().into(photo);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    // Method that load after a failed nutrientRequest that displays the error message
    @Override
    public void gotNutrientsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
