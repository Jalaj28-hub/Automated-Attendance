package com.example.biometriclogin

import android.content.Intent
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
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

    fun updateUserData(classcode:String) {
        val userHashMap = HashMap<String, Any>()
        if (userHashMap[classcode] != 0L) {
            userHashMap[classcode] = FieldValue.increment(1)
            // how to show that value?
        }
        updateUserAtt(this, userHashMap)
    }
    fun updateUserAtt(activity: ScanQRKotlin, userHashMap: HashMap<String, Any>) {
        mFirestore.collection("users")
            .document(res1)
            .update(userHashMap)
            .addOnSuccessListener {
                Toast.makeText(activity, "Updated", Toast.LENGTH_SHORT).show()
                val intent= Intent(this,Student_present::class.java)
                mFirestore.collection("users").document(res1).get().addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null) {

                            var name:String=document.get("name").toString()
                            var email:String=document.get("email").toString()
                            intent.putExtra("name",name)
                            intent.putExtra("email",email)
                            startActivity(intent)
//                            finish()
                        } else {
                            Toast.makeText(this,"Nhi mila kuch", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(this,"Nhi h kuch", Toast.LENGTH_LONG).show()
                    }
                }
//                intent.putExtra("name","Jalaj")
//                intent.putExtra("email","20128@iiitu.ac.in")
//                startActivity(intent)
//                finish()
            }.addOnFailureListener {
                Toast.makeText(activity, "failed to update", Toast.LENGTH_LONG).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qractivity2)
        supportActionBar!!.hide()
        //The key argument here must match that used in the other activity
        val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo

        wifiInfo = wifiManager.connectionInfo
        val ssid: String
        ssid = wifiInfo.bssid
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        tv = findViewById<TextView>(R.id.tvscan)
        val fireclass= Firestoreclass()
        mCodeScanner = CodeScanner(this, scannerView)

        mCodeScanner.setDecodeCallback(DecodeCallback { result ->
            runOnUiThread {

                val res2:String = result.text.toString()
                val yourArray: List<String> = res2.split("_")
                res1=yourArray[0]
                val classcode:String=yourArray[1];
//                Toast.makeText(this,"${fireclass.getdata()}",Toast.LENGTH_LONG).show()

//                intent.putExtra("t", res1)
                if(yourArray[2]==ssid){
                    tv.setText("Attendance marked")
                    updateUserData(classcode)
                }
                else{
                    Toast.makeText(this@ScanQRKotlin, "Connect to the same wifi scanned is connected", Toast.LENGTH_LONG).show()
                }
            }
        })
        scannerView.setOnClickListener { mCodeScanner.startPreview() }
    }

    override fun onResume() {
        super.onResume()
        mCodeScanner!!.startPreview()
    }
    override fun onBackPressed() {
        val i = Intent(this, AdminSide::class.java)
        startActivity(i)
        finish()
    }

    override fun onPause() {
        mCodeScanner!!.releaseResources()
        super.onPause()
    }
}