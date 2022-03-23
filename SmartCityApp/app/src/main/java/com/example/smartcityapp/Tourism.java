package com.example.smartcityapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tourism#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tourism extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context mContext;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tourism() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tourism.
     */
    // TODO: Rename and change types and number of parameters
    public static Tourism newInstance(String param1, String param2) {
        Tourism fragment = new Tourism();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_tourism, container, false);
        TextView t = (TextView) inf.findViewById(R.id.t);
         // The textview
        Button button = (Button) inf.findViewById(R.id.button); // The button
        RequestQueue queue = Volley.newRequestQueue(mContext);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET, "https://api.opentripmap.com/0.1/en/places/radius?radius=2000&lon=-83.357604&lat=33.9519&apikey=5ae2e3f221c38a28845f05b634ee2da680014417a5342d1496cdc86a", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject a = new JSONObject(response);
                            JSONArray b = a.getJSONArray("features");

                            for (int i = 0; i < b.length(); i++) {
                                JSONObject c = b.getJSONObject(i);
                                JSONObject d = c.getJSONObject("properties");
                                t.append(d.getString("name"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //t.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
            }
        });



        return inf;
    }

   }