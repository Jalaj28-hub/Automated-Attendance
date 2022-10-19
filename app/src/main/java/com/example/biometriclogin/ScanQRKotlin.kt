package com.example.biometriclogin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.example.biometriclogin.firebase.Firestoreclass
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

open class ScanQRKotlin : AppCompatActivity() {
    private lateinit var mCodeScanner: CodeScanner
    private lateinit var tv: TextView

    private var mFirestore = FirebaseFirestore.getInstance()


    var res1 = ""

    fun updateUserData() {
        val userHashMap = HashMap<String, Any>()
        if (userHashMap["cspe12"] != 0L) {
            userHashMap["cspe12"] = FieldValue.increment(1)
            // how to show that value?
        }
        updateUserAtt(this, userHashMap)
    }
    fun updateUserAtt(activity: ScanQRKotlin, userHashMap: HashMap<String, Any>) {
        mFirestore.collection("users")
            .document(res1)
            .update(userHashMap)
            .addOnSuccessListener {
                Toast.makeText(activity, "Updated", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(activity, "failed to update", Toast.LENGTH_LONG).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qractivity2)
        supportActionBar!!.hide()
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        tv = findViewById<TextView>(R.id.tvscan)
        val fireclass= Firestoreclass()
        mCodeScanner = CodeScanner(this, scannerView)

        mCodeScanner.setDecodeCallback(DecodeCallback { result ->
            runOnUiThread {
                tv.setText("Attendance marked")
                res1 = result.text.toString()
//                Toast.makeText(this,"${fireclass.getdata()}",Toast.LENGTH_LONG).show()

                intent.putExtra("t", res1)
                updateUserData()
//                Toast.makeText(this@ScanQRKotlin, "$res1", Toast.LENGTH_LONG).show()
            }
        })
        scannerView.setOnClickListener { mCodeScanner.startPreview() }
    }

    override fun onResume() {
        super.onResume()
        mCodeScanner!!.startPreview()
    }

    override fun onPause() {
        mCodeScanner!!.releaseResources()
        super.onPause()
    }
}