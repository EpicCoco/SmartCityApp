package com.example.smartcityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    TextInputEditText u;
    TextInputEditText p;
    Button b;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        u = findViewById(R.id.u);
        p = findViewById(R.id.a);
        b = findViewById(R.id.button4);
        mAuth = FirebaseAuth.getInstance();
        b.setOnClickListener(view -> {
            createUser();
        });

    }
    private void createUser() {
        String email = u.getText().toString();
        String password = p.getText().toString();
        if (TextUtils.isEmpty(email)){
            u.setError("Email cannot be empty");
            u.requestFocus();
        } else if (TextUtils.isEmpty(password)){
            p.setError("Password cannot be empty");
            p.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "User registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}