package com.example.biometriclogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooseActivity extends AppCompatActivity {
    private ImageView admin;
    private ImageView student;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_NETWORK_STATE
                        ,Manifest.permission.INTERNET,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION
                        },
                PackageManager.PERMISSION_GRANTED);


        admin=findViewById(R.id.admin);
        student=findViewById(R.id.student);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChooseActivity.this,admin_login.class);
                startActivity(intent);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChooseActivity.this,LogIn.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
         finishAffinity();
    }
}