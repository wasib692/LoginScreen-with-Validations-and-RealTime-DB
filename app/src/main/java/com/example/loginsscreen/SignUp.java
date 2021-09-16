package com.example.loginsscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.*;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    TextInputLayout regName, regUsername, regPassword, regphoneNo, regGmail;
    Button regButton, regToLoginButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //hooks of sign_up xml

        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("Users");

        regName = (TextInputLayout) findViewById(R.id.reg_name);
        regUsername = (TextInputLayout)findViewById(R.id.reg_username);
        regPassword = (TextInputLayout)findViewById(R.id.reg_pass_word);
        regphoneNo = (TextInputLayout)findViewById(R.id.reg_phone);
        regGmail = (TextInputLayout)findViewById(R.id.reg_email);
        regButton = findViewById(R.id.reg_btn);
        regToLoginButton = findViewById(R.id.reg_toLogin_btn);






    }

    //validation
    private Boolean validateName() {
        String name = Objects.requireNonNull(regName.getEditText()).getText().toString();
        if (name.isEmpty()) {
            regName.setError("Field cannot be Empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String username = Objects.requireNonNull(regUsername.getEditText()).getText().toString();
        String noWhiteSpace="\\A\\w{4,20}\\z";
        if (username.isEmpty()) {
            regUsername.setError("Field cannot be Empty");
            return false;
        }else if(username.length()>=15){
            regUsername.setError("Username too long");
            return false;

        }else if(!username.matches(noWhiteSpace)){
            regUsername.setError("Whitespaces are not allowed");
            return false;

        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String email = Objects.requireNonNull(regGmail.getEditText()).getText().toString();
        String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            regGmail.setError("Field cannot be Empty");
            return false;
        }else if(!email.matches(emailpattern)){
            regGmail.setError("Invalid Email Address");
            return false;
        }
        else {
            regGmail.setError(null);

            return true;
        }
    }

    private Boolean validatePhoneno() {
        String phoneNo = Objects.requireNonNull(regphoneNo.getEditText()).getText().toString();

        if (phoneNo.isEmpty()) {
            regphoneNo.setError("Field cannot be Empty");
            return false;
        }
        else {
            regphoneNo.setError(null);

            return true;
        }
    }

    private Boolean validatePassword() {
        String password = Objects.requireNonNull(regPassword.getEditText()).getText().toString();
        String passwordval="^" +
                "(?=.*[@#$%^&+=])" +     // at least 1 special character
                "(?=\\S+$)" +            // no white spaces
                ".{4,}" +                // at least 4 characters
                "$";
        if (password.isEmpty()) {
            regPassword.setError("Field cannot be Empty");
            return false;
        }else if(!password.matches(passwordval)){
            regPassword.setError("Password is too weak");
            return false;

        }
        else {
            regPassword.setError(null);

            return true;
        }
    }

    //save data in firebase onclick
    public void registerUser(View view) {
        //save data in firebase on button click
        if(!validateName() | !validateUsername() | !validateEmail() | !validatePhoneno() | !validatePassword()){
           return;
        }


        String name = Objects.requireNonNull(regName.getEditText()).getText().toString();
        String username = Objects.requireNonNull(regUsername.getEditText()).getText().toString();
        String email = Objects.requireNonNull(regGmail.getEditText()).getText().toString();
        String phoneNo = Objects.requireNonNull(regphoneNo.getEditText()).getText().toString();
        String password = Objects.requireNonNull(regPassword.getEditText()).getText().toString();

        Intent intent=new Intent(getApplicationContext(),VerifyPhoneNo.class);
        intent.putExtra("phoneNo",phoneNo);
        startActivity(intent);

        //JavaHelperClass javaHelperClass = new JavaHelperClass(name, username, email, phoneNo, password);
       // reference.child(username).setValue(javaHelperClass);
       // Toast.makeText(this, "Your account has been created successfully ", Toast.LENGTH_SHORT).show();
    }
}