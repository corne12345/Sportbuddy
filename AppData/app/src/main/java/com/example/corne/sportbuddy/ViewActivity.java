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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ViewActivity extends AppCompatActivity implements NutrientsRequest.Callback{

    String calories;
    String fat;
    String carbohydrates;
    String protein;
    String sodium;
    String servingSize;
    String servingWeight;
    float totalCalories;

//    Set servings as standard to 1 in case user doesn't give an input
    int servings = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent gotIntent = getIntent();
        totalCalories = (float) gotIntent.getSerializableExtra("calories");
        Log.e("Developer", String.valueOf(totalCalories));
        String food = gotIntent.getSerializableExtra("choice").toString();
        TextView textView = findViewById(R.id.textView18);
        textView.setText(food);

        NutrientsRequest x = new NutrientsRequest(this, food);
        x.getNutrients( ViewActivity.this);
        Log.e("Developer", "getNutrients done");

        final EditText amountServings = findViewById(R.id.editText3);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewActivity.this, InputActivity.class);
                startActivity(intent1);
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String servingsString = (String) amountServings.getText().toString();
                if (servingsString.length() > 0){
                    servings = Integer.valueOf(servingsString);
                }
                totalCalories += Float.valueOf(calories) * servings;
                Log.e("Developer", String.valueOf(totalCalories));

                Intent intent2 = new Intent(ViewActivity.this, InputActivity.class);
                intent2.putExtra("calories", totalCalories);
                startActivity(intent2);
            }
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String servingsString = (String) amountServings.getText().toString();
                if (servingsString.length() > 0){
                    servings = Integer.valueOf(servingsString);
                }
                totalCalories += Float.valueOf(calories) * servings;
                Log.e("Developer", String.valueOf(totalCalories));

                Intent intent3 = new Intent (ViewActivity.this, SportActivity.class);
                intent3.putExtra("calories", totalCalories);
                startActivity(intent3);
            }
        });
    }

    @Override
    public void gotNutrients(final JSONObject responseObject){
        Log.e("Developer", "Succesfull request");
        final TextView caloriesView = findViewById(R.id.textView9);
        final TextView fatView = findViewById(R.id.textView11);
        final TextView carbohydratesView = findViewById(R.id.textView13);
        final TextView proteinView = findViewById(R.id.textView15);
        final TextView sodiumView = findViewById(R.id.textView17);
        final TextView servingSizeView = findViewById(R.id.textView20);
        final TextView servingWeightView = findViewById(R.id.textView22);

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

            JSONObject photoArray = responseObject.getJSONObject("photo");
            String urlPhoto = photoArray.getString("highres");
            Log.e("Developer", urlPhoto);
            ImageView photo = findViewById(R.id.imageView2);
            Picasso.get().load(urlPhoto).into(photo);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void gotNutrientsError(String message) {
        Log.e("Developer", "Failed Request");
    }
}
