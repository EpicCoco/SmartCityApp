package com.example.smartcityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Login page.
 */
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void login(View view) {
        Intent intent = new Intent(this, /** Activity to go to.*/ MainActivity.class);
        startActivity(intent); // Switch to that tab.
    }
}