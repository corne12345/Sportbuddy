package com.example.corne.sportbuddy;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.widget.ImageView;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NutrientsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private String input;
    private Callback activity;
    private String mRequestBody;



    @Override
    public void onResponse(JSONObject response) {
        JSONObject responseObject = null;
        try {
            JSONArray responseArray = response.getJSONArray("foods");
            responseObject = (JSONObject) responseArray.get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        activity.gotNutrients(responseObject);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotNutrientsError(error.getMessage());

    }

    public interface Callback {
        void gotNutrients(JSONObject output);
        void gotNutrientsError(String message);
    }

    public NutrientsRequest(Context context, String input) {
        this.context = context;
        this.input = input;
    }

    void getNutrients(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String URL = "https://trackapi.nutritionix.com/v2/natural/nutrients";
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("query", input);
            jsonBody.put("timezone", "Central European Time");
            mRequestBody = jsonBody.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null, this, this){

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
        queue.add(jsonObjectRequest);
    }
}