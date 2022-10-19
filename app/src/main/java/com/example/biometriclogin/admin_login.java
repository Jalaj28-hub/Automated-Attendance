package com.example.biometriclogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login extends AppCompatActivity {
    private TextInputEditText admin_email;
    private TextInputEditText admin_password;
    private Button admin_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        getSupportActionBar().hide();
        admin_email=findViewById(R.id.tv_EmailLog);
        admin_password=findViewById(R.id.tv_PasswordLog);
        admin_btn=findViewById(R.id.btn_LogIn);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = admin_email.getText().toString();
                String pass = admin_password.getText().toString();
                if(email.equals("admin@gmail.com")){
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(admin_login.this,AdminSide.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(admin_login.this, "Something went wrong! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(admin_login.this,"Not An Admin",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}