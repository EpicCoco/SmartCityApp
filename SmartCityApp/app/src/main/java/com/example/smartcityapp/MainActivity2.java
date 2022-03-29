package com.example.smartcityapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Login page.
 */
public class MainActivity2 extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button button;
    TextInputEditText et;
    TextInputEditText p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        button = (Button) findViewById(R.id.button2);
        et = findViewById(R.id.username);
        p = findViewById(R.id.password);
        Button r = (Button) findViewById(R.id.r);
        r.setOnClickListener((view -> {
            startActivity(new Intent(MainActivity2.this,Register.class));
        }));
        button.setOnClickListener(view -> {
            String email = et.getText().toString();
            String password = p.getText().toString();
            if (TextUtils.isEmpty(email)) {
                et.setError("Email cannot be empty");
                et.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                p.setError("Email cannot be empty");
                p.requestFocus();
            } else {
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity2.this,"Login Successful",Toast.LENGTH_LONG);
                            startActivity(new Intent(MainActivity2.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(MainActivity2.this,task.getException().getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                });
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //startActivity(new Intent(MainActivity2.this,Register.class));
    }

    public void login(View view) {
        Intent intent = new Intent(this, /** Activity to go to.*/ MainActivity.class);
        startActivity(intent); // Switch to that tab.
    }
}