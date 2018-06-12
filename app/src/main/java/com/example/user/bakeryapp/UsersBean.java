package com.example.user.bakeryapp;

/**
 * Created by User on 7/24/2017.
 */

public class UsersBean {

    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;
    public String toString()
    {
        return username+"  "+email;
    }
}
