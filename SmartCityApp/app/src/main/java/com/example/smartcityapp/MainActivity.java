package com.example.smartcityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView t = (TextView) findViewById(R.id.t);
        Button button = (Button) findViewById(R.id.button);
        RequestQueue q = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://api.opentripmap.com/0.1/en/places/radius?radius=2000&lon=-83.357604&lat=33.9519&apikey=5ae2e3f221c38a28845f05b634ee2da680014417a5342d1496cdc86a";
                JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET,url,null,
                        new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject r) {
                        try {
                            JSONArray j = r.getJSONArray("features");
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject a = j.getJSONObject(i);
                                JSONObject b = a.getJSONObject("properties");
                                String c = b.getString("name");
                                t.append(c + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                        }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError e) {
                        e.printStackTrace();
                    }
                });
                q.add(r);
            }
        });
    }
}