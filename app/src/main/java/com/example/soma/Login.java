package com.example.soma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button login_btn;
    EditText auth_mail,auth_pass;
    private ProgressDialog mDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth_mail = findViewById(R.id.email_login);
        auth_pass = findViewById(R.id.password_login);
        login_btn = findViewById(R.id.btn_login);
        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail= auth_mail.getText().toString().trim();
                String mPass=auth_pass.getText().toString().trim();
                if(TextUtils.isEmpty(mEmail)){
                    auth_mail.setError("Required field");
                    return;
                }
                if(TextUtils.isEmpty(mPass)){
                    auth_pass.setError("Required field");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),tutor_profile.class));

                            mDialog.dismiss();

                        }else{
                            Toast.makeText(getApplicationContext(),"failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();

                        }


                    }
                });
            }
        });
    }
}
