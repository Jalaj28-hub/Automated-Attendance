package com.example.biometriclogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle;


class AllAttendance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_attendance)
        getSupportActionBar()?.hide();
    }
}