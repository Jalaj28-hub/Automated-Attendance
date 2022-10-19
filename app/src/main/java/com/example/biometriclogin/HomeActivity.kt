package com.example.biometriclogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private var scanqr: Button? = null
    private var generateqr: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_home)
        scanqr = findViewById(R.id.IdBtnGenerater)
        generateqr = findViewById(R.id.IdBtnScanner)
        scanqr?.setOnClickListener(View.OnClickListener {
            val `in` = Intent(this@HomeActivity, ScanQRKotlin::class.java)
            startActivity(`in`)
        })
        generateqr?.setOnClickListener(View.OnClickListener {
            val `in` = Intent(this@HomeActivity, GenerateQRActivity::class.java)
            startActivity(`in`)
        })
    }
}