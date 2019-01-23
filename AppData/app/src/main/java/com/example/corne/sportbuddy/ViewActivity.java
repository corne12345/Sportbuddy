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

public class ViewActivity extends AppCompatActivity{

    String calories;
    String fat;
    String carbohydrates;
    String protein;
    String sodium;
    String servingSize;
    String servingWeight;
    float totalCalories;


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

        final TextView caloriesView = findViewById(R.id.textView9);
        final TextView fatView = findViewById(R.id.textView11);
        final TextView carbohydratesView = findViewById(R.id.textView13);
        final TextView proteinView = findViewById(R.id.textView15);
        final TextView sodiumView = findViewById(R.id.textView17);
        final TextView servingSizeView = findViewById(R.id.textView20);
        final TextView servingWeightView = findViewById(R.id.textView22);

        final EditText amountServings = findViewById(R.id.editText3);

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("query", food);
            jsonBody.put("timezone", "Central European Time");
            final String mRequestBody = jsonBody.toString();
            String URL = "https://trackapi.nutritionix.com/v2/natural/nutrients";

            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray responseArray = response.getJSONArray("foods");
                        JSONObject responseObject = (JSONObject) responseArray.get(0);
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
                        Log.e("Developer", "Fail");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    Log.e("Developer", error.getMessage());
                    Log.e("Developer", String.valueOf(error));
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("x-app-id", "a39f9714");
                    params.put("x-app-key", "2332c372fcee1f71ae2e959125eacc97");
//                    params.put("Content-Type", "application/json");
                    params.put("x-remote-user-id", "0");
                    return params;
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                int servings = Integer.valueOf(servingsString);
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
                int servings = Integer.valueOf(servingsString);
                totalCalories += Float.valueOf(calories) * servings;
                Log.e("Developer", String.valueOf(totalCalories));

                Intent intent3 = new Intent (ViewActivity.this, SportActivity.class);
                intent3.putExtra("calories", totalCalories);
                startActivity(intent3);
            }
        });
    }
}
