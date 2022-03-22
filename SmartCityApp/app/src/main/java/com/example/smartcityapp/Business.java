package com.example.smartcityapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Business#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Business extends Fragment {
    TextView t;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Business() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Business.
     */
    // TODO: Rename and change types and number of parameters
    public static Business newInstance(String param1, String param2) {
        Business fragment = new Business();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_business, container, false);
        TextView tv = (TextView) inf.findViewById(R.id.t);
        InputStream is = getResources().openRawResource(R.raw.jobs);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try{
            List<String[]> resultList = new ArrayList<String[]>();
            String c = "";
            //tv.append(br.readLine());
            while ((c = br.readLine()) != null) {
                String[] row = c.split(",");
                resultList.add(row);
            }
            Map<Integer, String> a = new HashMap<Integer, String>();
            LinkedHashMap<Integer, String> b = new LinkedHashMap<>();

            for (int i = 0; i < resultList.size(); i++) {
                a.put(i,resultList.get(i)[3]);
            }
            a.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> b.put(x.getKey(), x.getValue()));
            //tv.append(b.toString());
            Map.Entry<Integer, String> actualValue = b.entrySet()
                    .stream()
                    .skip(2).findFirst()
                    .get();
            tv.append(resultList.get(actualValue.getKey())[0]);



        } catch (Exception e) {

        }
        return inf;
    }
    public void setText(String text){
        TextView textView = (TextView) getView().findViewById(R.id.t);
        textView.setText(text);
    }
}