package com.example.smartcityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView chatsRV;
    private ImageButton sendMsgIB;
    private EditText userMsgEdt;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private RequestQueue mRequestQueue;

    // creating a variable for array list and adapter class.
    private ArrayList<MessageModal> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        mRequestQueue.getCache().clear();

        // creating a new array list
        messageModalArrayList = new ArrayList<>();
        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if the message entered
                // by user is empty or not.
                if (userMsgEdt.getText().toString().isEmpty()) {
                    // if the edit text is empty display a toast message.
                    Toast.makeText(MainActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // calling a method to send message
                // to our bot to get response.
                sendMessage(userMsgEdt.getText().toString());

                // below line we are setting text in our edit text as empty
                userMsgEdt.setText("");
            }
        });
        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);

        // below line we are creating a variable for our linear layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);

        // below line is to set layout
        // manager to our recycler view.
        chatsRV.setLayoutManager(linearLayoutManager);

        // below line we are setting
        // adapter to our recycler view.
        chatsRV.setAdapter(messageRVAdapter);

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
            //test
        }
    };
    private void sendMessage(String userMsg) {
        // below line is to pass message to our
        // array list which is entered by the user.
        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();

        // url for our brain
        // make sure to add mshape for uid.
        // make sure to add your url.
        String url = "\n" +
                "http://api.brainshop.ai/get?bid=165180&key=VQWM2uITQC3WWwoD&uid=[uid]&msg=[msg]" + userMsg;

        // creating a variable for our request queue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // on below line we are making a json object request for a get request and passing our url .
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // in on response method we are extracting data
                    // from json response and adding this response to our array list.
                    String botResponse = response.getString("cnt");
                    messageModalArrayList.add(new MessageModal(botResponse, BOT_KEY));
                    messageRVAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();

                    // handling error response from bot.
                    messageModalArrayList.add(new MessageModal("No response", BOT_KEY));
                    messageRVAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error handling.
                messageModalArrayList.add(new MessageModal("Sorry no response found", BOT_KEY));
                Toast.makeText(MainActivity.this, "No response from the bot..", Toast.LENGTH_SHORT).show();
                Log.e("a",error.getMessage());
            }
        });

        // at last adding json object
        // request to our queue.
        queue.add(jsonObjectRequest);
    }

                }