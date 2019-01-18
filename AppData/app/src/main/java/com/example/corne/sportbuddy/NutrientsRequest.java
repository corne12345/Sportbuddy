package com.example.corne.sportbuddy;

        import android.content.Context;
        import android.util.Log;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.VolleyLog;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.UnsupportedEncodingException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;

/*
Objects of this class can do POST requests with parameters.
*/
public class NutrientsRequest extends StringRequest implements Response.ErrorListener, Response.Listener<String> {

    String mRequestBody = "{\"query\":\"cake\"}";
    private Callback activity;
    private Context context;


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

    }


    public interface Callback {
        void gotNutrients(ArrayList<String> nutrients);
        void gotNutrientsError(String message);
    }

    // Constructor
    public NutrientsRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    void getNutrients(Callback activity){
        this.activity = activity;
        String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";
        RequestQueue queue = Volley.newRequestQueue(context);
        NutrientsRequest request = new NutrientsRequest(Request.Method.POST, url, this, this);
        queue.add(request);

    }

    // Method to supply parameters to the request
    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("x-app-id", "a39f9714");
        params.put("x-app-key", "8bb9875979d2a2bf0adb3cdc00c6c14f");
        params.put("x-remote-user-id", "0");
        params.put("Content-type", "application/json");
        params.put("cache-control", "no-cache");
        params.put("Postman-Token", "65477895-99bf-4522-85a2-0a63a55ff7f7");
        return params;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
            return null;
        }
    }
}