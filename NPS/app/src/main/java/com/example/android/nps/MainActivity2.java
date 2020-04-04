package com.example.android.nps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//3 Buttons on Screen : Plant Identification, Plant Information, Generate Bill

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button1 = (Button) findViewById(R.id.plant_identification_button);
        button2 = (Button) findViewById(R.id.plant_information_button);
        button3 = (Button) findViewById(R.id.generate_bill_button);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent i;
        switch(id) {
            case R.id.plant_identification_button:
                i = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(i);
                break;
            case R.id.plant_information_button:
                i = new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(i);
                break;
            case R.id.generate_bill_button:
                i = new Intent(MainActivity2.this, MainActivity7.class);
                startActivity(i);
                break;
            default:
                break;
        }

    }
}
