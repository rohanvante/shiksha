package com.example.schoolapp.Model;
public class Usermodel {
    String username,useremai,userpass;

    public Usermodel() {
    }

    public Usermodel(String username, String useremai, String userpass) {
        this.username = username;
        this.useremai = useremai;
        this.userpass = userpass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremai() {
        return useremai;
    }

    public void setUseremai(String useremai) {
        this.useremai = useremai;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }
}