package com.example.user.bakeryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.Calendar;

public class Form extends AppCompatActivity
{
    EditText txtname,txtmail,txtphone;
    String[] pname;
    int[]  price;
    BakeryManager bm;
    SQLiteDatabase sb;
    String name,email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        txtmail=(EditText)findViewById(R.id.txtmail);
        txtname=(EditText)findViewById(R.id.txtname);
        txtphone=(EditText)findViewById(R.id.txtphone);
        bm=new BakeryManager(this);
        sb=bm.openDb();
        Intent obj=getIntent();
        pname=obj.getStringArrayExtra("name");
        price=obj.getIntArrayExtra("prc");
    }
    public void order(View v)
    {
        name= txtname.getText().toString();
         email=txtmail.getText().toString();
         phone=txtphone.getText().toString();

        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int date=c.get(Calendar.DATE);
        c.set(year, month, date);
        String l=date+"/"+month+"/"+year;
       // long l=c.getTimeInMillis();
        for(int i=0;i<price.length;i++)
        {
            if(price[i]!=0)
            {
                ContentValues cv=new ContentValues();
                cv.put(BakeryConstants.ORDER_COL_USERNAME,name);
                cv.put(BakeryConstants.ORDER_COL_NAME,pname[i]);
                cv.put(BakeryConstants.ORDER_COL_DATE,l);
                cv.put(BakeryConstants.ORDER_COL_AMOUNT,price[i]);
                long rw= sb.insert(BakeryConstants.ORDER_TBL_NAME,null,cv);
                if(rw>0)
                {
                    Toast.makeText(this," order placed"+rw,Toast.LENGTH_LONG).show();
                }
            }

        }
        user();
    }
    public void user()
    {
        ContentValues cv1=new ContentValues();
        cv1.put(BakeryConstants.USER_COL_NAME,name);
        cv1.put(BakeryConstants.USER_COL_PHONE,phone);
        cv1.put(BakeryConstants.USER_COL_EMAIL,email);
        long rw1= sb.insert(BakeryConstants.USER_TBL_NAME,null,cv1);
        if(rw1>0)
        {
            Toast.makeText(this,"user added",Toast.LENGTH_LONG).show();
        }

    }
}
