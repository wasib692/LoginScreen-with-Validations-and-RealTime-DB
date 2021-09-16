package com.example.loginsscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ProfileUser extends AppCompatActivity {
    TextInputLayout fullname,Gmail,Phone,Password;
    TextView fullname_label,username_label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        //hooks
        fullname=findViewById(R.id.profile_name);
        Gmail=findViewById(R.id.profile_gmail);
        Phone=findViewById(R.id.profile_phone);
        Password=findViewById(R.id.profile_password);
        fullname_label=findViewById(R.id.full_name);
        username_label=findViewById(R.id.profileUN);

        //showalldata
        showAllUserData();

    }

    private void showAllUserData() {
        Intent intent=getIntent();
        String user_name=intent.getStringExtra("name");
        String user_username=intent.getStringExtra("username");
        String user_password=intent.getStringExtra("password");
        String user_phoneNo=intent.getStringExtra("phoneNo");
        String user_email=intent.getStringExtra("email");

        fullname_label.setText(user_name);
        username_label.setText(user_username);
        fullname.getEditText().setText(user_name);
        Gmail.getEditText().setText(user_email);
        Phone.getEditText().setText(user_phoneNo);
        Password.getEditText().setText(user_password);


    }
}