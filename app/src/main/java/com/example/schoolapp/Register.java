package com.example.schoolapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolapp.Model.Usermodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText name,regemail,regpassword;
    Button registerbtn;
    ImageView gotosignin;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        name=findViewById(R.id.regname);
        regemail=findViewById(R.id.regemil);
        regpassword=findViewById(R.id.regpass);
        registerbtn=findViewById(R.id.register);
        gotosignin=findViewById(R.id.gosignin);
        gotosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        String username = name.getText().toString();
        String useremai = regemail.getText().toString();
        String userpass = regpassword.getText().toString();
        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(useremai))
        {
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(userpass))
        {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
        }
        auth.createUserWithEmailAndPassword(useremai,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Usermodel usermodel = new Usermodel(username,useremai,userpass);
                    String id = task.getResult().getUser().getUid();
                    firebaseDatabase.getReference().child("Users").child(id).setValue(usermodel);
                    Toast.makeText(Register.this, "Register Sucessfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                    finish();
                }else {
                    Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

