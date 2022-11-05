package com.example.biometriclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;
import java.util.Locale;


public class GenerateQRActivity extends AppCompatActivity {

    private ImageView qrCodeIV;
    private EditText dataEdt;
    private Button generateQrBtn;
    Bitmap bitmap;
    private Button update;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qractivity);
        getSupportActionBar().hide();
        qrCodeIV = findViewById(R.id.IVQrGenerator);
        generateQrBtn = findViewById(R.id.IdBtnGenerater);
        generateQrBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                generateQR();
            }
        });




//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                updateUserData();
//                Toast.makeText(GenerateQRActivity.this, "Updated", Toast.LENGTH_LONG).show();
//            }
//
//            fun updateUserData(){
//                val userHashMap = HashMap<String, Any>()
//                if(userHashMap["image"]!=0L){
//                    userHashMap["image"] = FieldValue.increment(1)
//                    // how to show that value?
//                }
//                Firestoreclass().updateUserData(this, userHashMap)
//            }
//        });

    }



    private void generateQR() {

        String classcode=" ";
            classcode = getIntent().getStringExtra("classcode").toLowerCase(Locale.ROOT);
            //The key argument here must match that used in the other activity

        String text = FirebaseAuth.getInstance().getCurrentUser().getUid()+"_"+classcode;
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 400, 400);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            qrCodeIV.setImageBitmap(bitmap);
            Toast.makeText(GenerateQRActivity.this,text,Toast.LENGTH_LONG).show();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


}