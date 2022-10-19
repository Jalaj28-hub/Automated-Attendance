package com.example.biometriclogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.biometriclogin.firebase.Firestoreclass
import com.google.firebase.firestore.FieldValue


class update : AppCompatActivity() {

//    fun updateUserData(){
//        val userHashMap = HashMap<String, Any>()
//        if(userHashMap["cspe12"]!=0L){
//            userHashMap["cspe12"] = FieldValue.increment(1)
//            // how to show that value?
//        }
//        Firestoreclass().updateUserData(this, userHashMap)
//    }

//    fun updateUserData2(){
//        val userHashMap = HashMap<String, Any>()
//        if(userHashMap["cspe22"]!=0L){
//            userHashMap["cspe22"] = FieldValue.increment(1)
//            // how to show that value?
//        }
//        Firestoreclass().updateUserData(this, userHashMap)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        var cspe12: Button =findViewById(R.id.cspe12);
        var cspe22: Button =findViewById(R.id.cspe22);
        cspe12.setOnClickListener(View.OnClickListener {
//            updateUserData()
        }
        )
        cspe22.setOnClickListener(View.OnClickListener {
//            updateUserData2()
        }
        )



    }
}