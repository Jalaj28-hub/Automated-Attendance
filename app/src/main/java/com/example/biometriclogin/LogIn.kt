package com.example.biometriclogin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class LogIn : AppCompatActivity() {
    lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        auth= FirebaseAuth.getInstance();
        var tvEmail:TextInputEditText=findViewById(R.id.tv_EmailLog);
        var tvPass:TextInputEditText=findViewById(R.id.tv_PasswordLog);
        var btnLogIn:Button=findViewById(R.id.btn_LogIn);

        val currentuser= auth.currentUser
        if(currentuser != null) {
            startActivity(Intent(this@LogIn,HomeActivity::class.java))
            this.finish()
        }


        getSupportActionBar()?.hide();
         val tvRegisterLink:TextView=findViewById(R.id.tv_RegisterLink)

        btnLogIn.setOnClickListener {
 //           btnLogIn.setTextColor(Color.parseColor("#FFFFF"))
//            btnLogIn.setBackgroundColor(Color.parseColor("#0099FF"))
            it?.apply { isEnabled = false; postDelayed({ isEnabled = true }, 400) }

            if(TextUtils.isEmpty(tvEmail.text.toString())) {
                tvEmail.error = "Please enter email"
                return@setOnClickListener
            }else if(TextUtils.isEmpty(tvPass.text.toString())) {
                tvPass.error="Please enter password "
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(tvEmail.text.toString(),tvPass.text.toString())
            .addOnCompleteListener{
                if(it.isSuccessful){
                    val intent=Intent(this@LogIn,HomeActivity::class.java)
                    startActivity(intent)
                    this.finish()
//                    Toast.makeText(this@LogIn,"Registration is succesfull.", Toast.LENGTH_LONG).show()
                }
                else{
//                    Toast.makeText(this@LogIn,"LogIn is not succesfull.Please try again",Toast.LENGTH_LONG).show()
                }
            }
        }


        tvRegisterLink.setOnClickListener(View.OnClickListener {
            tvRegisterLink.setTextColor(Color.parseColor("#0099FF"));
            val intent=Intent(this@LogIn,Registration::class.java)
            startActivity(intent)
            this.finish()
        })
    }

}