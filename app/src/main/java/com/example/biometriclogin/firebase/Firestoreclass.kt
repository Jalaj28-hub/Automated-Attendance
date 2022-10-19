package com.example.biometriclogin.firebase

import android.util.Log
import android.widget.Toast
import com.example.biometriclogin.Registration
import com.example.biometriclogin.ScanQRKotlin
import com.example.biometriclogin.User
import com.example.biometriclogin.update
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class Firestoreclass : ScanQRKotlin() {
    private val mFirestore = FirebaseFirestore.getInstance()


    fun registerUser(activity: Registration, userInfo: User) {
        mFirestore.collection("users")
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnCompleteListener {
                activity.userRegistrationSuccess()
            }
    }

    fun getCurrentUserId(): String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""

        if (currentUser != null)
            currentUserId = currentUser.uid
        return currentUserId
    }


    fun getUserId(): String {
        val intent = intent
        val str = intent.getStringExtra("t")
        Toast.makeText(this@Firestoreclass, "$str", Toast.LENGTH_LONG).show()
        return str.toString()
    }

    fun updateUserData(activity: ScanQRKotlin, userHashMap: HashMap<String, Any>) {
        mFirestore.collection("users")
            .document(getUserId())
            .update(userHashMap)
            .addOnSuccessListener {
                Toast.makeText(activity, "Updated", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(activity, "failed to update", Toast.LENGTH_LONG).show()
            }
    }

//    fun updateUserAttendance(activity: ScanQRKotlin, userHashMap: HashMap<String, Any>) {
//        mFirestore.collection("users")
//            .document(getUserId())
//            .update(userHashMap)
//            .addOnSuccessListener {
//                Toast.makeText(activity, "updated", Toast.LENGTH_LONG).show()
//            }.addOnFailureListener {
//                Toast.makeText(activity, "failed to update", Toast.LENGTH_LONG).show()
//            }
//    }


}