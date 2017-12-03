package com.hlacab.hla;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hlacab.hla.common.Common;

public class Profile extends AppCompatActivity {
    Button signin, register;
    EditText username, password;
    FirebaseAuth auth;
    String user, pass;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
db=FirebaseDatabase.getInstance();
users=db.getReference(Common.user_rider_tb1);

        signin = (Button) findViewById(R.id.signin);
        register = (Button) findViewById(R.id.register);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "LOGIN SUCCESS", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Welcome.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "LOGIN FAILED", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "SignUp Failed" + task.getException(), Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(getApplicationContext(), Welcome.class));

                        }
                    }
                });
            }
        });

    }
}
