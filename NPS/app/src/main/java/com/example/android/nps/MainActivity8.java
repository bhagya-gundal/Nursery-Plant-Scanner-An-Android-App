package com.example.android.nps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//Bill

public class MainActivity8 extends AppCompatActivity {

    ListView plants_listview, quantities_listview, prices_listview;
    TextView total_textview;

    ArrayList<String> plants;
    ArrayList<Integer> quantities;
    ArrayList<Integer> prices;
    int total_price;

    ArrayAdapter<String> plants_adapter;
    ArrayAdapter<Integer> quantities_adapter;
    ArrayAdapter<Integer> prices_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        plants_listview = (ListView) findViewById(R.id.plants_listview);
        quantities_listview = (ListView) findViewById(R.id.quantities_listview);
        prices_listview = (ListView) findViewById(R.id.prices_listview);
        total_textview = (TextView) findViewById(R.id.total_textview);

        plants = new ArrayList<String>();
        quantities = new ArrayList<Integer>();
        prices = new ArrayList<Integer>();

        Intent intent = getIntent();
        plants = intent.getStringArrayListExtra("plants");
        quantities = intent.getIntegerArrayListExtra("quantities");
        prices = intent.getIntegerArrayListExtra("prices");
        total_price = intent.getIntExtra("total_price", 0);

        plants_adapter = new ArrayAdapter<String>(this,R.layout.list_layout,plants);
        quantities_adapter = new ArrayAdapter<Integer>(this,R.layout.list_layout,quantities);
        prices_adapter = new ArrayAdapter<Integer>(this,R.layout.list_layout,prices);

        plants_listview.setAdapter(plants_adapter);
        quantities_listview.setAdapter(quantities_adapter);
        prices_listview.setAdapter(prices_adapter);

        total_textview.setText("Total : " + Integer.toString(total_price) + "/-");
    }
}
