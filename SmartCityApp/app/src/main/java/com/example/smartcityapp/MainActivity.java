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

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new Business()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        /**
         * Selects the fragment that the user should see when they click a button on the bottom.
         * @param item What tab the user selects.
         * @return the item that should be shown
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = new Business();
            switch (item.getItemId()) {
                case R.id.search: // business module
                    selectedFragment = new Business();
                    break;
                case R.id.tourism: // tourism module
                    selectedFragment = new Tourism();
                    break;
                case R.id.more:
                    selectedFragment = new Education(); // Education module
                    break;
                case R.id.jobs:
                    selectedFragment = new Jobs();
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