package com.example.user.bakeryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

public class Prodwise extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner sp;
    ArrayList<ProBean> catlist;
    ProBean cb,cb1;
    BakeryManager bm;
    SQLiteDatabase sb;
    String catname;
    Snackbar sn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodwise);
        sp = (Spinner) findViewById(R.id.sp);
        catlist = new ArrayList<>();
        fillList();
        ArrayAdapter<ProBean> ad = new ArrayAdapter<ProBean>(this, android.R.layout.simple_spinner_item, catlist);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);
    }
    public void fillList() {
        bm = new BakeryManager(this);
        sb = bm.openDb();
        cb1=new ProBean();
        cb1.setPname("select one");
        catlist.add(cb1);
        Cursor c = sb.query(BakeryConstants.PRO_TBL_NAME, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.PRO_COL_NAME));
                cb = new ProBean();
                cb.setPname(name);
                catlist.add(cb);

            } while (c.moveToNext());
            c.close();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        catname = catlist.get(position).toString();
    }
    public void go(View v)
    {
        if(catname.equalsIgnoreCase("Select one"))
        {
            sn=Snackbar.make(v,"Please select any option",Snackbar.LENGTH_INDEFINITE);//remains on screen untill perform some action
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
            Intent i = new Intent(this, OrderProd.class);
            i.putExtra("msg", catname);
            startActivity(i);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
