package com.example.biometriclogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Classes extends AppCompatActivity {
       private CardView cspe12;
    private CardView cspe22;
    private CardView cspe32;
    private TextView tv_cspe12;
    private TextView tv_cspe22;
    private TextView tv_cspe32;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_classes);
        cspe12=findViewById(R.id.cv_cspe12);
        cspe22=findViewById(R.id.cv_cspe22);
        cspe32=findViewById(R.id.cv_cspe32);
        tv_cspe12=findViewById(R.id.cspe12);
        tv_cspe22=findViewById(R.id.cspe22);
        tv_cspe32=findViewById(R.id.cspe32);
        String cs12 = tv_cspe12.getText().toString();
        String cs22 = tv_cspe22.getText().toString();
        String cs32 = tv_cspe32.getText().toString();


        cspe12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Classes.this,GenerateQRActivity.class);
                i.putExtra("classcode",cs12);
                startActivity(i);
            }
        });
        cspe22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Classes.this,GenerateQRActivity.class);
                i.putExtra("classcode",cs22);
                startActivity(i);
            }
        });
        cspe32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Classes.this,GenerateQRActivity.class);
                i.putExtra("classcode",cs32);
                startActivity(i);
            }
        });


    }
}