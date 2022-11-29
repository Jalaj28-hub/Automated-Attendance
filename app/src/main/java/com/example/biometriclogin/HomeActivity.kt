package com.example.biometriclogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class HomeActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    private var Showatt: Button? = null
    private var generateqr: Button? = null

    //The key argument here must match that used in the other activity
    var dp = FirebaseAuth.getInstance().currentUser!!.uid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_home)
        Showatt = findViewById(R.id.IdBtnGenerater)
        generateqr = findViewById(R.id.IdBtnScanner)
        Showatt?.setOnClickListener(View.OnClickListener {
            val `in` = Intent(this@HomeActivity, AttendaceList1::class.java)
            startActivity(`in`)
            this.finish()
        })
        generateqr?.setOnClickListener(View.OnClickListener {
            val `in` = Intent(this@HomeActivity, Classes::class.java)
            startActivity(`in`)
            this.finish()
        })
    }
}