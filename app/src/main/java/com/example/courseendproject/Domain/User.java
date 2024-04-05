package com.example.courseendproject.Domain;

import android.view.View;

public class User {
    private String user,mail;
    public User(String user,String mail) {
        this.user = user;
        this.mail = mail;
    }

    public String getUser() {
        return user;
    }



    public void setUser(String user) {
        this.user = user;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
