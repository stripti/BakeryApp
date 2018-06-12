package com.example.user.bakeryapp;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class Type extends AppCompatActivity implements TextToSpeech.OnInitListener {

    Button btnad,btnus;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        tts=new TextToSpeech(this,this);

        btnad=(Button)findViewById(R.id.btnad);
        btnus=(Button)findViewById(R.id.btnus);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
    public void admin(View v)
    {

        Intent i=new Intent(this,Login.class);
        startActivity(i);
    }
    public void user(View v)
    {
        tts.speak("Welcome user!!",TextToSpeech.QUEUE_FLUSH,null);
        Intent i=new Intent(this,Userpage.class);
        startActivity(i);
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
