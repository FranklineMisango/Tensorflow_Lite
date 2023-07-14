package com.example.dl_classifier;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button selectBtn, predictBtn, captureBtn;
    TextView result;
    ImageView imageview;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectBtn = findViewById(R.id.selectbtn);
        predictBtn = findViewById(R.id.predictbtn);
        captureBtn = findViewById(R.id.capturebtn);
        result = findViewById(R.id.result);
        imageview = findViewById(R.id.imageview);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("Image/*");
                startActivityForResult(intent, 10);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        if (resultCode==10) {
            if (data != null){
                Uri uri = data.getData();
                try{
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    imageview.setImageBitmap(bitmap);
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }
}