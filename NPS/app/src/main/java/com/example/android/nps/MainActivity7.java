package com.example.android.nps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//Add Plants to Cart

public class MainActivity7 extends AppCompatActivity implements View.OnClickListener {

    EditText plant_to_buy, quantity;
    Button add_to_cart_button;
    String DB_NAME = Environment.getExternalStorageDirectory() + "/NPS/plants.db";
    String TABLE_NAME = "myplantstable";
    String search_string;
    int quantity_val, price_val, total_price = 0;
    ArrayList<String> plants;
    ArrayList<Integer> quantities;
    ArrayList<Integer> prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        plant_to_buy = (EditText) findViewById(R.id.plant_to_buy);
        quantity = (EditText) findViewById(R.id.quantity);
        add_to_cart_button = (Button) findViewById(R.id.add_to_cart_button);

        add_to_cart_button.setOnClickListener(this);

        plants = new ArrayList<String>();
        quantities = new ArrayList<Integer>();
        prices = new ArrayList<Integer>();
    }

    @Override
    public void onClick(View v) {
        search_string = plant_to_buy.getText().toString();
        if(search_string.equals(""))
            Toast.makeText(this.getBaseContext(), "Enter valid plant name",
                    Toast.LENGTH_SHORT).show();
        else if(quantity.getText().toString().equals(""))
            Toast.makeText(this.getBaseContext(), "Enter valid quantity",
                    Toast.LENGTH_SHORT).show();
        else {
            quantity_val = Integer.parseInt(quantity.getText().toString());
            readFromDB();
        }
    }

    void readFromDB() {
        SQLiteDatabase myDb;
        myDb = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        String sql_query = "select * from " + TABLE_NAME + " where plant_name like '%" +
                search_string + "%'";
        Cursor cur = myDb.rawQuery(sql_query, null);
        cur.moveToFirst();

        if (cur.getCount() > 0) {
            price_val = cur.getInt(cur.getColumnIndex("price"));
            total_price += quantity_val * price_val;
            plants.add(search_string);
            quantities.add(quantity_val);
            prices.add(quantity_val * price_val);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to buy more plants?")
                    .setTitle("Added to cart");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    plant_to_buy.setHint(R.string.search_hint);
                    quantity.setHint("Enter quantity");
                }
            });
            builder.setNegativeButton("Checkout", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(MainActivity7.this,MainActivity8.class);
                    intent.putStringArrayListExtra("plants", plants);
                    intent.putIntegerArrayListExtra("quantities",quantities);
                    intent.putIntegerArrayListExtra("prices",prices);
                    intent.putExtra("total_price",total_price);
                    startActivity(intent);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        else {
            Toast.makeText(this.getBaseContext(), "Plant not found in database",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
