package com.example.biometriclogin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.View


class AttendaceList1 : AppCompatActivity() {
    private var cspe12:String?=null
    private var cspe22:String?=null
    private var cspe32:String?=null
    private var percent_cspe12: TextView?=null
    private var percent_cspe22: TextView?=null
    private var percent_cspe32: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendace_list1)
        var percentButton:Button=findViewById(R.id.button2)
        var totalclass:TextInputEditText=findViewById(R.id.totalclass)
        percent_cspe12=findViewById(R.id.percent)
        percent_cspe22=findViewById(R.id.percent1)
        percent_cspe32=findViewById(R.id.percent2)
        val db = FirebaseFirestore.getInstance()
        val dp = FirebaseAuth.getInstance().currentUser!!.uid
        val docRef: DocumentReference = db.collection("users").document(dp.toString())
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    cspe12= document.get("cspe12").toString()
                    cspe22= document.get("cspe22").toString()
                    cspe32= document.get("cspe32").toString()
                    percent_cspe12?.setText(cspe12.toString())
                    percent_cspe22?.setText(cspe22.toString())
                    percent_cspe32?.setText(cspe32.toString())
                } else {
                    Toast.makeText(this@AttendaceList1,"Nhi mila kuch", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@AttendaceList1,"Nhi h kuch", Toast.LENGTH_LONG).show()
            }
        }

        percentButton.setOnClickListener{
          var total:String = totalclass.text.toString()
            var totalclass:Int=total.toInt()
            var cspe12class:Int= cspe12!!.toInt()
            var cspe22class:Int= cspe22!!.toInt()
            var cspe32class:Int= cspe32!!.toInt()
            cspe12class=(cspe12class*100)/totalclass
            cspe22class=(cspe22class*100)/totalclass
            cspe32class=(cspe32class*100)/totalclass
            if(cspe12class<75){
               percent_cspe12?.setTextColor(Color.parseColor("#FF00000"))
            }
            if(cspe22class<75){
                percent_cspe22?.setTextColor(Color.parseColor("#FF0000"))
            }
            if(cspe32class<75){
                percent_cspe32?.setTextColor(Color.parseColor("#FF0000"))
            }
            percent_cspe12?.setText(cspe12class.toString()+"%")
            percent_cspe22?.setText(cspe22class.toString()+"%")
            percent_cspe32?.setText(cspe32class.toString()+"%")


        }

    }
}


