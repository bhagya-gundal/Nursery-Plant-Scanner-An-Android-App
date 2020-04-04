package com.example.android.nps;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//2 Buttons on Screen : Capture Image from Camera, Load Image from Gallery

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    Button image_option1, image_option2;
    private String selectedImagePath, imgPath;
    private static final int SELECT_PICTURE = 1;
    private static final int CAPTURE_PICTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        image_option1 = (Button) findViewById(R.id.image_option1);
        image_option2 = (Button) findViewById(R.id.image_option2);

        image_option1.setOnClickListener(this);
        image_option2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id) {
            case R.id.image_option1:
                //code to capture image from camera
                Intent camera_intent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        setImageUri());
                startActivityForResult(camera_intent, CAPTURE_PICTURE);
                break;

            case R.id.image_option2:
                //code to load image from gallery
                Intent gallery_intent = new Intent();
                gallery_intent.setType("image/*");
                gallery_intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(gallery_intent, "Select Picture"),
                        SELECT_PICTURE);
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
                intent.putExtra("imageUri",selectedImageUri.toString());
                intent.putExtra("selectedImagePath", selectedImagePath);
                startActivity(intent);
            }

            else if (requestCode == CAPTURE_PICTURE) {
                selectedImagePath = getImagePath();
                Uri capturedImageUri = Uri.parse(selectedImagePath);
                Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
                intent.putExtra("imageUri", capturedImageUri.toString());
                intent.putExtra("selectedImagePath", selectedImagePath);
                startActivity(intent);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public Uri setImageUri() {

        File image_dir = new File(Environment.getExternalStorageDirectory() + "/NPS/images");
        if(!image_dir.exists()) {
            if (image_dir.mkdir()) {
                Log.d("MainActivity3","Directory is created!");
            } else {
                Log.d("MainActivity3","Failed to create directory!");
            }
        }

        File file = new File(Environment.getExternalStorageDirectory() + "/NPS/images", "image" +
                new Date().getTime() + ".jpg");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }

    public String getImagePath() {
        return imgPath;
    }
}
