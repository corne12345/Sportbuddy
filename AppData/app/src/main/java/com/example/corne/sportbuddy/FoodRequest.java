package com.example.corne.sportbuddy;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    Callback activity;
    ArrayList<JSONObject> foodJSON = new ArrayList<>();
    ArrayList<String> food = new ArrayList<>();
    ArrayList<String> servingUnit = new ArrayList<>();
    String input;

    // Constructor
    public FoodRequest(Context context, String input) {
        this.context = context;
        this.input = input;
    }

    // Callback method
    public interface Callback {
        void gotFood(ArrayList<String> food, ArrayList<String> servingUnit);
        void gotFoodError(String message);
    }

    // Method that makes a queue and tries to add this request to it
    void getFood(Callback activity){
        this.activity = activity;
        String url = "https://trackapi.nutritionix.com/v2/search/instant?query="+ input;
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this){

                // Make a HashMap containing the required headerss
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("x-app-id", "a39f9714");
                    params.put("x-app-key", "2332c372fcee1f71ae2e959125eacc97");
                    params.put("x-remote-user-id", "0");
                    params.put("cache-control", "no-cache");
                    return params;
                }
            };
            queue.add(jsonObjectRequest);

        } catch (Exception error) {
            Log.e("Developer", error.getMessage());
        }
    }


    // Connect the error to the correct method in the InputActivity
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotFoodError(error.getMessage());
    }

    // Action on Response
    @Override
    public void onResponse(JSONObject response) {

        // build 2 Arraylists for the foods and their serving sizes
        try {
            JSONArray foodArray = response.getJSONArray("common");
            for(int i = 0; i < foodArray.length(); i ++){
                JSONObject temp = foodArray.getJSONObject(i);
                foodJSON.add(temp);
                String foodString = temp.getString("food_name");
                food.add(foodString);
                String servingUnitString = temp.getString("serving_unit");
                servingUnit.add(servingUnitString);
            }
        } catch (JSONException e) {
            Log.e("Developer", e.getMessage());
        }

        // load the corresponing method in InputActivity with the ArrayLists as parameters
        activity.gotFood(food, servingUnit);
    }
}
