package com.example.user.bakeryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Login extends AppCompatActivity implements TextToSpeech.OnInitListener {


    EditText id, pass;
    SharedPreferences sp;
    SharedPreferences.Editor se;
    TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tts=new TextToSpeech(this,this);

        id = (EditText) findViewById(R.id.txtuserid);
        pass = (EditText) findViewById(R.id.txtuserpass);
        sp = getSharedPreferences("admininfo", MODE_APPEND);
        se = sp.edit();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
    public void submit(View v) {
        String ui = id.getText().toString();
        String ipass = pass.getText().toString();
        int upass=Integer.parseInt(ipass);

        String userid = sp.getString("userid", null);
        String sppass = sp.getString("userpass", null);
        int sharepass=Integer.parseInt(sppass);

        if (ui.equals(userid) && (upass==sharepass)) {
            tts.speak("Hello Admin!!", TextToSpeech.QUEUE_FLUSH,null);

            Intent i = new Intent(this, Adminpage.class);
            startActivity(i);
        } else {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("ALERT!!");
            ad.setMessage("Invalid Userid or Password");
            ad.setNeutralButton("OK", null);
            AlertDialog a = ad.create();
            a.show();
        }
    }

    @Override
    public void onInit(int status) {
        int res= tts.setLanguage(Locale.ENGLISH);
        if(res==TextToSpeech.LANG_NOT_SUPPORTED ||res==TextToSpeech.LANG_MISSING_DATA)
        {
            Toast.makeText(this,"not supported",Toast.LENGTH_LONG).show();
        }
    }
}
