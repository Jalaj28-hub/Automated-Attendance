package com.example.biometriclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.zxing.client.android.Intents;

public class AdminSide extends AppCompatActivity {
    private Button scanner;
    private Button showAtt;
    private Button present;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_side);
        scanner=findViewById(R.id.IdBtnScanner);
        showAtt=findViewById(R.id.showAtt);
        present=findViewById(R.id.btn_present);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminSide.this, ScanQRKotlin.class);
                startActivity(intent);
                finish();
            }
        });
        showAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminSide.this, AllAttendance.class);
                startActivity(intent);
            }
        });
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminSide.this,Student_present.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(AdminSide.this,ChooseActivity.class);
        startActivity(intent);
        finish();
    }
}