package com.example.loginsscreen;

public class JavaHelperClass {
    String name;
    String username;
    String password;
    String email;
    String phoneNo;

    public JavaHelperClass() {
    }


    public JavaHelperClass(String name, String username, String password, String email, String phoneNo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}

