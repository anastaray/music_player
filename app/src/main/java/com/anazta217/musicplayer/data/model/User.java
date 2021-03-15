package com.anazta217.musicplayer.data.model;

import android.util.Patterns;

public class User {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }
}
