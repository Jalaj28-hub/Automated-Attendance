//package com.example.biometriclogin;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.budiyev.android.codescanner.CodeScanner;
//import com.budiyev.android.codescanner.CodeScannerView;
//import com.budiyev.android.codescanner.DecodeCallback;
//import com.google.firebase.firestore.FieldValue;
//import com.google.protobuf.Any;
//import com.google.zxing.Result;
//
//import java.util.HashMap;
//
//public class ScanQRActivity extends AppCompatActivity {
//    private CodeScanner mCodeScanner;
//    private TextView tv;
//
//
//    @Override
//
//
//
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan_qractivity2);
//        getSupportActionBar().hide();
//        CodeScannerView scannerView = findViewById(R.id.scanner_view);
//        tv=findViewById(R.id.tvscan);
//        mCodeScanner = new CodeScanner(this, scannerView);
//        mCodeScanner.setDecodeCallback(new DecodeCallback() {
//            @Override
//            public void onDecoded(@NonNull final Result result) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv.setText(result.getText());
//                    }
//                });
//            }
//        });
//        scannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCodeScanner.startPreview();
//            }
//        });
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mCodeScanner.startPreview();
//    }
//
//    @Override
//    protected void onPause() {
//        mCodeScanner.releaseResources();
//        super.onPause();
//    }
//}