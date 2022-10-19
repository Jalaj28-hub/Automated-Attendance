package com.example.biometriclogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    lateinit var btnAuth:Button;
    lateinit var tvAuthStatus:TextView;

    lateinit var executor: Executor;
    lateinit var biometricPrompt:androidx.biometric.BiometricPrompt
    lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();
        btnAuth=findViewById(R.id.btnAuth)
       tvAuthStatus=findViewById(R.id.showtext)
        executor= ContextCompat.getMainExecutor(this)
        biometricPrompt=androidx.biometric.BiometricPrompt( this@MainActivity,executor,object:androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                tvAuthStatus.text="Error :"+errString;
            }
            override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                tvAuthStatus.text="Success";
                val intent=Intent(this@MainActivity,ChooseActivity::class.java)
                startActivity(intent)
                finish()
            }
            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                tvAuthStatus.text="Authentication Failed"
            }
        })

        promptInfo=androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using fingerprint")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
        btnAuth.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

    }
}