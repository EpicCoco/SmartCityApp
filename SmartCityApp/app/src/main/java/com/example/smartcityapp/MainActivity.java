package com.example.smartcityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.view.MenuItem;
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
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        TextView t = (TextView) findViewById(R.id.t); // The textview
        Button button = (Button) findViewById(R.id.button); // The button
        RequestQueue q = Volley.newRequestQueue(this); // RequestQueue to make the API work
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = "https://api.opentripmap.com/0.1/en/places/radius?radius=2000&lon=-83.357604&lat=33.9519&apikey=5ae2e3f221c38a28845f05b634ee2da680014417a5342d1496cdc86a";
                JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET,url,null,
                        new Response.Listener<JSONObject>() {
                            /**
                             * This method gets the names of the places that are in the specified city.
                             * @param r The JSON object created by the API.
                             */
                    public void onResponse(JSONObject r) {
                        try {
                            JSONArray j = r.getJSONArray("features");
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject a = j.getJSONObject(i); // get the details of each place
                                JSONObject b = a.getJSONObject("properties"); // get the properties
                                String c = b.getString("name"); // get the name of the location
                                t.append(c + "\n"); // make the names appear
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
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = new Business();
            switch (item.getItemId()) {
                case R.id.search:
                    selectedFragment = new Business();
                    break;
                case R.id.more:
                    selectedFragment = new Tourism();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, selectedFragment)
                    .commit();
            return true;
        }
    };

}