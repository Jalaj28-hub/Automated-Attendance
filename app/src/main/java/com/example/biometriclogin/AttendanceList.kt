package com.example.biometriclogin

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class AttendanceList : AppCompatActivity() {
    private var cspe12:Int?=null
    private var cspe22:Int?=null
    private var cspe32:Int?=null
    private var percent_cspe12:TextView?=null
    private var percent_cspe22:TextView?=null
    private var percent_cspe32:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_list)
        percent_cspe12=findViewById(R.id.percent)
        percent_cspe22=findViewById(R.id.percent1)
        percent_cspe32=findViewById(R.id.percent2)
        //The key argument here must match that used in the other activity
        val dp = FirebaseAuth.getInstance().currentUser!!.uid
        val db=FirebaseFirestore.getInstance()
        val docRef: DocumentReference = db.collection("users").document(dp)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                 cspe12= document!!.get("cspe12") as Int?
                    cspe22= document!!.get("cspe22") as Int?
                    cspe32= document!!.get("cspe32") as Int?
                    Toast.makeText(this,cspe12.toString(),Toast.LENGTH_LONG).show()
                    percent_cspe12?.setText(cspe12.toString())
                    percent_cspe22?.setText(cspe22.toString())
                    percent_cspe32?.setText(cspe32.toString())

                } else {
                    Toast.makeText(this@AttendanceList,"Nhi mila kuch", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@AttendanceList,"Nhi h kuch", Toast.LENGTH_LONG).show()
            }
        }
    }
}
