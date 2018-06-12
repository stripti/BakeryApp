package com.example.user.bakeryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;
import java.util.Locale;

public class Userpage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner sp;
    ArrayList<CategoryBean> catlist;
    CategoryBean cb,cb1;
    BakeryManager bm;
    SQLiteDatabase sb;
    int[] price;
    String[] name;
    String catname;
    int cid;
    Snackbar sn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        sp = (Spinner) findViewById(R.id.sp);
        sp.setPrompt("select");
        catlist = new ArrayList<>();
        fillList();
        ArrayAdapter<CategoryBean> ad = new ArrayAdapter<CategoryBean>(this, android.R.layout.simple_spinner_item, catlist);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);
       // sp.setEnabled(false);
    }


    public void fillList() {
        bm = new BakeryManager(this);
        sb = bm.openDb();
        cb1=new CategoryBean();
          cb1.setCname("select one");
          catlist.add(cb1);
        Cursor c = sb.query(BakeryConstants.CAT_TBL_NAME, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.CAT_COL_NAME));

                cb = new CategoryBean();


                cb.setCname(name);

                catlist.add(cb);

            } while (c.moveToNext());
            c.close();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        catname = catlist.get(position).toString();
        String[] args = {catname};
        Cursor c = sb.query(BakeryConstants.CAT_TBL_NAME, null, BakeryConstants.CAT_COL_NAME + " =?", args, null, null, null);
        if (c != null && c.moveToFirst()) {
            cid = c.getInt(c.getColumnIndex(BakeryConstants.CAT_COL_ID));
        }
        c.close();
    }
    public void go(View v)
    {
        if(catname.equalsIgnoreCase("Select one"))
        {
            sn= Snackbar.make(v,"Please select any option",Snackbar.LENGTH_INDEFINITE);//remains on screen untill perform some action
            sn.setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            sn.setActionTextColor(Color.GREEN);
            View v1=sn.getView();
            TextView tv=(TextView)v1.findViewById(android.support.design.R.id.snackbar_text) ;
            tv.setTextColor(Color.YELLOW);
            sn.show();
        }
        else {

            Intent i = new Intent(this, Product.class);
            i.putExtra("msg", cid);
            startActivity(i);
        }

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        price=data.getIntArrayExtra("prc");
        name=data.getStringArrayExtra("name");
       Toast.makeText(this,"Bhej dia :)",Toast.LENGTH_LONG).show();
        for (int i = allname.length; i < name.length; i++)
        {
            allname[i]=name[i];
          //  System.out.println(allname[i]);
        }
    }*/

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        Toast.makeText(this,"Nothing was selected",Toast.LENGTH_LONG).show();

    }


}
