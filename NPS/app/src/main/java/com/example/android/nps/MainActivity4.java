package com.example.android.nps;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Search Screen

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener{

    String DB_NAME = Environment.getExternalStorageDirectory() + "/NPS/plants.db";
    String TABLE_NAME = "myplantstable";

    Button search_button;
    EditText search_box;

    String search_string;
    String plant_name, description, common_name, color, bloom_time, height,
            difficulty_level, planting_and_care, sunlight, soil, water, temperature, fertilizer,
            special_feature, medicinal_use, imageURi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        search_button = (Button) findViewById(R.id.search_button);
        search_box = (EditText) findViewById(R.id.search_box);

        search_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //code to search plant in database, display results in new activity
        search_string = search_box.getText().toString();
        if(search_string.equals(""))
            Toast.makeText(this.getBaseContext(),"Enter valid search query",
                    Toast.LENGTH_SHORT).show();
        else
            readFromDB();
    }

    void readFromDB() {
        SQLiteDatabase myDb;
        myDb = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        String sql_query = "select * from " + TABLE_NAME + " where plant_name like '%" +
                search_string +"%'";
        Cursor cur = myDb.rawQuery(sql_query, null);
        cur.moveToFirst();

        if(cur.getCount()>0) {
            plant_name = cur.getString(cur.getColumnIndex("plant_name"));
            description = cur.getString(cur.getColumnIndex("description"));
            common_name = cur.getString(cur.getColumnIndex("common_name"));
            color = cur.getString(cur.getColumnIndex("color"));
            bloom_time = cur.getString(cur.getColumnIndex("bloom_time"));
            height = cur.getString(cur.getColumnIndex("height"));
            difficulty_level = cur.getString(cur.getColumnIndex("difficulty_level"));
            planting_and_care = cur.getString(cur.getColumnIndex("planting_and_care"));
            sunlight = cur.getString(cur.getColumnIndex("sunlight"));
            soil = cur.getString(cur.getColumnIndex("soil"));
            water = cur.getString(cur.getColumnIndex("water"));
            temperature = cur.getString(cur.getColumnIndex("temperature"));
            fertilizer = cur.getString(cur.getColumnIndex("fertilizer"));
            special_feature = cur.getString(cur.getColumnIndex("special_feature"));
            medicinal_use = cur.getString(cur.getColumnIndex("medicinal_use"));
            imageURi = cur.getString(cur.getColumnIndex("imageUri"));

            Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
            intent.putExtra("plant_name",plant_name);
            intent.putExtra("description",description);
            intent.putExtra("common_name",common_name);
            intent.putExtra("color",color);
            intent.putExtra("bloom_time",bloom_time);
            intent.putExtra("height",height);
            intent.putExtra("difficulty_level",difficulty_level);
            intent.putExtra("planting_and_care",planting_and_care);
            intent.putExtra("sunlight",sunlight);
            intent.putExtra("soil",soil);
            intent.putExtra("water",water);
            intent.putExtra("temperature",temperature);
            intent.putExtra("fertilizer",fertilizer);
            intent.putExtra("special_feature",special_feature);
            intent.putExtra("medicinal_use",medicinal_use);
            intent.putExtra("imageUri",imageURi);
            startActivity(intent);
        }

        else
            Toast.makeText(this.getBaseContext(),"Not found in database",Toast.LENGTH_SHORT).show();

        cur.close();
        myDb.close();
    }
}
