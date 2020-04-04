package com.example.android.nps;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

//Plant Identification Form Screen

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener{

    String DB_NAME = Environment.getExternalStorageDirectory() + "/NPS/plants.db";
    String TABLE_NAME = "myplantstable";

    String selectedImagePath;

    ImageView image;
    Button save_in_db_button;
    EditText plant_name, description, common_name, color, bloom_time, height,
        difficulty_level, planting_and_care, sunlight, soil, water, temperature, fertilizer,
        special_feature, medicinal_use, price;
    String splant_name, sdescription, scommon_name, scolor, sbloom_time, sheight,
            sdifficulty_level, splanting_and_care, ssunlight, ssoil, swater, stemperature,
            sfertilizer, sspecial_feature, smedicinal_use;
    int price_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        image = (ImageView) findViewById(R.id.image);

        save_in_db_button = (Button) findViewById(R.id.save_in_db_button);

        plant_name = (EditText) findViewById(R.id.plant_name);
        description = (EditText) findViewById(R.id.description);
        common_name = (EditText) findViewById(R.id.common_name);
        color = (EditText) findViewById(R.id.color);
        bloom_time = (EditText) findViewById(R.id.bloom_time);
        height = (EditText) findViewById(R.id.height);
        difficulty_level = (EditText) findViewById(R.id.difficulty_level);
        planting_and_care = (EditText) findViewById(R.id.planting_and_care);
        sunlight = (EditText) findViewById(R.id.sunlight);
        soil = (EditText) findViewById(R.id.soil);
        water = (EditText) findViewById(R.id.water);
        temperature = (EditText) findViewById(R.id.temperature);
        fertilizer = (EditText) findViewById(R.id.fertilizer);
        special_feature = (EditText) findViewById(R.id.special_feature);
        medicinal_use = (EditText) findViewById(R.id.medicinal_use);
        price = (EditText) findViewById(R.id.price);

        save_in_db_button.setOnClickListener(this);

        Intent intent = getIntent();
        Uri imageUri = Uri.parse(intent.getStringExtra("imageUri"));
        selectedImagePath = intent.getStringExtra("selectedImagePath");
        image.setVisibility(View.VISIBLE);
        image.setImageURI(imageUri);
    }

    @Override
    public void onClick(View v) {
        int count = 0;
        splant_name = plant_name.getText().toString();
        if(splant_name.equals(""))
            count++;
        sdescription = description.getText().toString();
        if(sdescription.equals(""))
            count++;
        scommon_name = common_name.getText().toString();
        if(scommon_name.equals(""))
            count++;
        scolor = color.getText().toString();
        if(scolor.equals(""))
            count++;
        sbloom_time = bloom_time.getText().toString();
        if(sbloom_time.equals(""))
            count++;
        sheight = height.getText().toString();
        if(sheight.equals(""))
            count++;
        sdifficulty_level = difficulty_level.getText().toString();
        if(sdifficulty_level.equals(""))
            count++;
        splanting_and_care = planting_and_care.getText().toString();
        if(splanting_and_care.equals(""))
            count++;
        ssunlight = sunlight.getText().toString();
        if(ssunlight.equals(""))
            count++;
        ssoil = soil.getText().toString();
        if(ssoil.equals(""))
            count++;
        swater = water.getText().toString();
        if(swater.equals(""))
            count++;
        stemperature = temperature.getText().toString();
        if(stemperature.equals(""))
            count++;
        sfertilizer = fertilizer.getText().toString();
        if(sfertilizer.equals(""))
            count++;
        sspecial_feature = special_feature.getText().toString();
        if(sspecial_feature.equals(""))
            count++;
        smedicinal_use = medicinal_use.getText().toString();
        if(smedicinal_use.equals(""))
            count++;
        if(price.getText().toString().equals(""))
            count++;
        else
            price_val = Integer.parseInt(price.getText().toString());

        if(count==0) {
            //code to add to database
            createTable();
            saveInDB();
        }

        else {
            Toast.makeText(this,"Please enter all information",Toast.LENGTH_SHORT).show();
        }
    }

    void createTable() {
        SQLiteDatabase myDb = openOrCreateDatabase(DB_NAME,
                Context.MODE_PRIVATE, null);
        String MySQL = "create table if not exists "
                + TABLE_NAME
                + " (_id INTEGER primary key autoincrement, plant_name TEXT not null, " +
                "description TEXT not null, common_name TEXT not null, " +
                "color TEXT not null, bloom_time TEXT not null, height TEXT not null," +
                "difficulty_level TEXT not null, planting_and_care TEXT not null," +
                "sunlight TEXT not null, soil TEXT not null, water TEXT not null" +
                ",temperature TEXT not null, fertilizer TEXT not null," +
                "special_feature TEXT not null, medicinal_use TEXT not null," +
                "price INT not null, imageUri TEXT not null);";

        myDb.execSQL(MySQL);
        myDb.close();
    }

    void saveInDB() {
        SQLiteDatabase myDb = openOrCreateDatabase(DB_NAME,
                Context.MODE_PRIVATE, null);
        String s = myDb.getPath();
        ContentValues newValues = new ContentValues();
        newValues.put("plant_name", splant_name);
        newValues.put("description", sdescription);
        newValues.put("common_name", scommon_name);
        newValues.put("color", scolor);
        newValues.put("bloom_time", sbloom_time);
        newValues.put("height", sheight);
        newValues.put("difficulty_level", sdifficulty_level);
        newValues.put("planting_and_care", splanting_and_care);
        newValues.put("sunlight", ssunlight);
        newValues.put("soil", ssoil);
        newValues.put("water", swater);
        newValues.put("temperature", stemperature);
        newValues.put("fertilizer", sfertilizer);
        newValues.put("special_feature", sspecial_feature);
        newValues.put("medicinal_use", smedicinal_use);
        newValues.put("price", price_val);
        newValues.put("imageUri",selectedImagePath);

        long ret = myDb.insert(TABLE_NAME, null, newValues);
        if (ret < 0)
            Toast.makeText(this,"Error saving data",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this.getBaseContext(), "Data saved successfully",
                    Toast.LENGTH_SHORT).show();

        myDb.close();
    }
}
