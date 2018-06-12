package com.example.user.bakeryapp;

/**
 * Created by User on 7/24/2017.
 */

public class UserBean {
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;
    public String toString()
    {
        return username+"  "+phone;
    }
}
