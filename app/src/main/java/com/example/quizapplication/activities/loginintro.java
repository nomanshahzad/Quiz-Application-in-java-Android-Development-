package com.example.quizapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class loginintro extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginintro);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            Toast.makeText(this,"Already Logged in",Toast.LENGTH_SHORT).show();
            Redirect("Main");
        }

        Button getStarted = findViewById(R.id.btnGetStarted);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Redirect("Login");
            }
        });
    }

    private void Redirect(String name){
        if(name.equals("Login")){
            Intent launchLogin= new Intent(loginintro.this,loginActivity.class);
            startActivity(launchLogin);
            finish();
        }
        else if(name.equals("Main")){
            Intent launchmain= new Intent(loginintro.this,MainActivity.class);
            startActivity(launchmain);
            finish();
        }
        else {
            Toast.makeText(this,"Path not exist",Toast.LENGTH_SHORT).show();
        }
    }
}