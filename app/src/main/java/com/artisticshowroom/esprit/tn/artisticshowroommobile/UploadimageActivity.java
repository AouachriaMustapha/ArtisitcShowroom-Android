package com.artisticshowroom.esprit.tn.artisticshowroommobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artisticshowroom.esprit.tn.artisticshowroommobile.Entity.User;

public class UploadimageActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView buttonChoose;
    private TextView buttonUpload;
    boolean choose = false;
    private ImageView imageView;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;
    User user ;


    public static Context context;
    public static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);
        user = (User) getIntent().getSerializableExtra("User");
        buttonChoose = (TextView) findViewById(R.id.buttonChoose);
        buttonUpload = (TextView) findViewById(R.id.buttonUpload);
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("CandyCam/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonChoose){
            showFileChooser();
            choose=true;
        }}
}
