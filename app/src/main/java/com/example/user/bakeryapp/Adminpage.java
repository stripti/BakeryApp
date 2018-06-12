package com.example.user.bakeryapp;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class Adminpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mf=getMenuInflater();
        mf.inflate(R.menu.mymenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mimessage:
                Intent i=new Intent(this,Message.class);
                startActivity(i);
                return true;
            case R.id.micall:
                Intent i1=new Intent(this,Calling.class);
                startActivity(i1);
                return true;
            case R.id.mimail:
                Intent i2=new Intent(this,Mail.class);
                startActivity(i2);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}
    public void addcategory(View v)
    {
        Intent i=new Intent(this,AddCategory.class);
        startActivity(i);
    }
    public void addpro(View v)
    {
        Intent i=new Intent(this,AddProduct.class);
        startActivity(i);
    }
    public void modify(View v)
    {
        Intent i=new Intent(this,Modify.class);
        startActivity(i);
    }
    public void view1(View v)
    {
        Intent i=new Intent(this,View1.class);
        startActivity(i);
    }
    public void del(View v)
    {
        Intent i=new Intent(this,DeleteProduct.class);
        startActivity(i);
    }
    public void delcat(View v)
    {
        Intent i=new Intent(this,DeleteCategory.class);
        startActivity(i);
    }
    public void vieworder(View v)
    {
        Intent i=new Intent(this,ViewOrder.class);
        startActivity(i);
    }
    public void datewise(View v)
    {
        Intent i=new Intent(this,Datewise.class);
        startActivity(i);
    }
    public void prodwise(View v)
    {
        Intent i=new Intent(this,Prodwise.class);
        startActivity(i);
    }


}
