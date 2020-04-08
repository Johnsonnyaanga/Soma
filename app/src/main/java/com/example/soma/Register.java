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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Button register_btn;
    TextView loginLink;
    EditText auth_mail,auth_pass;
    private ProgressDialog mDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginLink = findViewById(R.id.signin_text);
        auth_mail = findViewById(R.id.email_register);
        auth_pass = findViewById(R.id.password_register);
        register_btn = findViewById(R.id.btn_register);
        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        register_btn.setOnClickListener(new View.OnClickListener() {
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

                mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                   startActivity(new Intent(getApplicationContext(),Login.class));
                            Toast.makeText(getApplicationContext(),"succesiful registeration",Toast.LENGTH_SHORT);
                            mDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT);

                    }
                });

            }
        });
    }
}
