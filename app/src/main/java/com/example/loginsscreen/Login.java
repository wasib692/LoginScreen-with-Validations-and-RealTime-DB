package com.example.loginsscreen;

import java.lang.reflect.Method;
import java.util.Objects;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Pair;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button sign_up, login;
    TextView logoText, sloganText;
    TextInputLayout userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //hooks
        sign_up = findViewById(R.id.callsignup);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });



        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair(sign_up, "signup_trans");
                pairs[1] = new Pair(logoText, "logo_text");
                pairs[2] = new Pair(sloganText, "slogan_trans");
                pairs[3] = new Pair(userName, "user_trans");
                pairs[4] = new Pair(password, "pass_trans");
                pairs[5] = new Pair(login, "go_btn");
                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                }
                startActivity(intent, options.toBundle());

            }
        });
    }

    private Boolean validateUsername() {
        String val = Objects.requireNonNull(userName.getEditText()).getText().toString();

        if (val.isEmpty()) {
            userName.setError("Field cannot be Empty");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = Objects.requireNonNull(password.getEditText()).getText().toString();

        if (val.isEmpty()) {
            password.setError("Field cannot be Empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser() {
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredUsername = userName.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    userName.setError(null);
                    userName.setErrorEnabled(false);


                    String passwordfromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordfromDB.equals(userEnteredPassword)) {

                        userName.setError(null);
                        userName.setErrorEnabled(false);

                        String namefromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernamefromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNofromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String gmailfromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), ProfileUser.class);
                        intent.putExtra("name", namefromDB);
                        intent.putExtra("email", gmailfromDB);
                        intent.putExtra("password", passwordfromDB);
                        intent.putExtra("phoneNo", phoneNofromDB);
                        intent.putExtra("username", usernamefromDB);

                        startActivity(intent);
                    } else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    userName.setError("No such User exists");
                    userName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}