package com.example.user.bakeryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor se;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sp=getSharedPreferences("admininfo", MODE_APPEND);
        se=sp.edit();
        se.putString("userid","tripti");
        se.putString("userpass","1234");
        se.commit();
        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(WelcomeActivity.this,Type.class);
                startActivity(i);
                WelcomeActivity.this.finish();
            }
        },2000);
    }
}
