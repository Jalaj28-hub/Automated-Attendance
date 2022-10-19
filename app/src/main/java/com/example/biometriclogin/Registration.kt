package com.example.biometriclogin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.biometriclogin.firebase.Firestoreclass
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Registration : AppCompatActivity() {
    lateinit var auth: FirebaseAuth;

    fun userRegistrationSuccess(){
        Toast.makeText(this, "Registration success", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance();

        setContentView(R.layout.activity_registration)
         getSupportActionBar()?.hide();
        val tvRegisterLink: TextView =findViewById(R.id.tv_LogInLink)
        val btnRegister:Button=findViewById(R.id.btn_LogIn)
        val tvName:TextInputEditText=findViewById(R.id.tv_Name)
        val tvEmail:TextInputEditText=findViewById(R.id.tv_EmailLog)
        val tvPassword:TextInputEditText=findViewById(R.id.tv_PasswordLog)
        val tvConfPassword:TextInputEditText=findViewById(R.id.tv_ConfirmPassword)

        btnRegister.setOnClickListener{
            if(TextUtils.isEmpty(tvName.text.toString())) {
                tvName.setError("Please enter first name ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(tvEmail.text.toString())) {
                tvEmail.setError("Please enter email ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(tvPassword.text.toString())) {
                tvPassword.setError("Please enter password ")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(tvConfPassword.text.toString())) {
                tvConfPassword.setError("Please enter confirm password ")
                return@setOnClickListener
            }

            val email: String = tvEmail.text.toString()
            val password: String = tvPassword.text.toString()
            val name: String = tvName.text.toString()

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val firebaseUser: FirebaseUser = it.result!!.user!!
                        val registeredEmail = firebaseUser.email!!
                        val user = User(firebaseUser.uid, name, registeredEmail)
                        Firestoreclass().registerUser(this, user)
                        val intent=Intent(this@Registration,HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@Registration,"Registration is succesfull.",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this@Registration,it.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }
        }

        tvRegisterLink.setOnClickListener(View.OnClickListener {
            tvRegisterLink.setTextColor(Color.parseColor("#0099FF"));
            val intent= Intent(this@Registration,LogIn::class.java)
            startActivity(intent)
            finish()
        })
    }
}