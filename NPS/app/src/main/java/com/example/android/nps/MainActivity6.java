package com.example.android.nps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//Plant Information Display Screen

public class MainActivity6 extends AppCompatActivity {

    ImageView image_display;
    TextView plant_name_textview, description_textview, common_name_textview, color_textview,
    bloom_time_textview, height_textview, difficulty_level_textview, planting_and_care_textview,
    sunlight_textview, soil_textview, water_textview, temperature_textview, fertilizer_textview,
    special_feature_textview, medicinal_use_textview;
    String plant_name, description, common_name, color, bloom_time, height,
            difficulty_level, planting_and_care, sunlight, soil, water, temperature, fertilizer,
            special_feature, medicinal_use, imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        image_display = (ImageView) findViewById(R.id.image_display);
        plant_name_textview = (TextView) findViewById(R.id.plant_name_textview);
        description_textview = (TextView) findViewById(R.id.description_textview);
        common_name_textview = (TextView) findViewById(R.id.common_name_textview);
        color_textview = (TextView) findViewById(R.id.color_textview);
        bloom_time_textview = (TextView) findViewById(R.id.bloom_time_textview);
        height_textview = (TextView) findViewById(R.id.height_textview);
        difficulty_level_textview = (TextView) findViewById(R.id.difficulty_level_textview);
        planting_and_care_textview = (TextView) findViewById(R.id.planting_and_care_textview);
        sunlight_textview = (TextView) findViewById(R.id.sunlight_textview);
        soil_textview = (TextView) findViewById(R.id.soil_textview);
        water_textview = (TextView) findViewById(R.id.water_textview);
        temperature_textview = (TextView) findViewById(R.id.temperature_textview);
        fertilizer_textview = (TextView) findViewById(R.id.fertilizer_textview);
        special_feature_textview = (TextView) findViewById(R.id.special_feature_textview);
        medicinal_use_textview = (TextView) findViewById(R.id.medicinal_use_textview);

        Intent intent = getIntent();
        plant_name = intent.getStringExtra("plant_name");
        description = intent.getStringExtra("description");
        common_name = intent.getStringExtra("common_name");
        color = intent.getStringExtra("color");
        bloom_time = intent.getStringExtra("bloom_time");
        height = intent.getStringExtra("height");
        difficulty_level = intent.getStringExtra("difficulty_level");
        planting_and_care = intent.getStringExtra("planting_and_care");
        sunlight = intent.getStringExtra("sunlight");
        soil = intent.getStringExtra("soil");
        water = intent.getStringExtra("water");
        temperature = intent.getStringExtra("temperature");
        fertilizer = intent.getStringExtra("fertilizer");
        special_feature = intent.getStringExtra("special_feature");
        medicinal_use = intent.getStringExtra("medicinal_use");
        imageUri = intent.getStringExtra("imageUri");

        plant_name_textview.setText("Plant Name : "+plant_name);
        description_textview.setText("Description : "+description);
        common_name_textview.setText("Common Name : "+common_name);
        color_textview.setText("Color : "+color);
        bloom_time_textview.setText("Bloom Time : "+bloom_time);
        height_textview.setText("Height : "+height);
        difficulty_level_textview.setText("Difficulty Level : "+difficulty_level);
        planting_and_care_textview.setText("Planting and Care : "+planting_and_care);
        sunlight_textview.setText("Sunlight : "+sunlight);
        soil_textview.setText("Soil : "+soil);
        water_textview.setText("Water : "+water);
        temperature_textview.setText("Temperature : "+temperature);
        fertilizer_textview.setText("Fertilizer : "+fertilizer);
        special_feature_textview.setText("Special Feature : "+special_feature);
        medicinal_use_textview.setText("Medicinal Use : " + medicinal_use);
        image_display.setImageBitmap(decodeFile(imageUri));

        Toast.makeText(this.getBaseContext(),
                "Data read from DB successfully.", Toast.LENGTH_SHORT).show();
    }

    public Bitmap decodeFile(String path) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 250;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE
                    && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeFile(path, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
