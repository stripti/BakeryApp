package com.example.user.bakeryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class ModifyPrice extends AppCompatActivity {

    Spinner spprod, spcat;
    EditText txtprice;
    CategoryBean cb;
    ProductBean pb;
    BakeryManager bm;
    SQLiteDatabase sb;
    ArrayList<CategoryBean> catlist;
    ArrayList<ProductBean> prolist;
    String catname,pname;
    int cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_price);
        spcat = (Spinner) findViewById(R.id.spcat);
        spprod = (Spinner) findViewById(R.id.spprod);
        txtprice = (EditText) findViewById(R.id.txtprice);
        catlist = new ArrayList<>();
        prolist = new ArrayList<>();
        bm = new BakeryManager(this);
        sb = bm.openDb();
        /*spcat.setOnItemSelectedListener(this);
        spprod.setOnItemSelectedListener(this);*/
        //fillList();
    }

    /*public void fillList() {
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
        ArrayAdapter<CategoryBean> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, catlist);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spcat.setAdapter(ad);
    }
*/
    /*public void fillList1() {
        String[] args = {String.valueOf(cid)};
        Cursor c = sb.query(BakeryConstants.PRO_TBL_NAME, null, BakeryConstants.PRO_COL_CAT_ID + " =?", args, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.PRO_COL_NAME));
                pb = new ProductBean();
                pb.setPname(name);
                prolist.add(pb);

            } while (c.moveToNext());
            c.close();
        }

        ArrayAdapter<ProductBean> ad1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prolist);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprod.setAdapter(ad1);
    }*/

   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

       // catname = spcat.getItemAtPosition(position).toString();

        catname=catlist.get(position).toString();

        Toast.makeText(this,catname,Toast.LENGTH_LONG).show();

        String[] args = {catname};

        Cursor c = sb.query(BakeryConstants.CAT_TBL_NAME, null, BakeryConstants.CAT_COL_NAME + " =?", args, null, null, null);
        if (c != null && c.moveToFirst())
        {
            cid = c.getInt(c.getColumnIndex(BakeryConstants.CAT_COL_ID));
          Toast.makeText(this,String.valueOf(cid),Toast.LENGTH_LONG).show();

        }
    c.close();
       // fillList1();


       *//*pname = spprod.getItemAtPosition(position).toString();
       String[] arg={String.valueOf(cid)};
        String[] arg1={pname};
        Cursor c1 = sb.query(BakeryConstants.PRO_TBL_NAME, null, BakeryConstants.PRO_COL_CAT_ID + " =?", arg, null, null, null);
        if (c1 != null && c1.moveToFirst())
        {
            Cursor c2 = sb.query(BakeryConstants.PRO_TBL_NAME, null, BakeryConstants.PRO_COL_NAME + " =?", arg1, null, null, null);
            if (c2 != null && c2.moveToFirst())
            {
                int price = c2.getInt(c2.getColumnIndex(BakeryConstants.PRO_COL_PRICE));
                txtprice.setText(price);

            }
            c2.close();
        }
        c1.close();
*//*
    }*/
    /*public void change(View v)
    {
        int fprice=Integer.parseInt(txtprice.getText().toString());
        String[] arg={String.valueOf(cid),pname};
        ContentValues cv=new ContentValues();
        cv.put(BakeryConstants.PRO_COL_PRICE,fprice);
        long l=sb.update(BakeryConstants.PRO_TBL_NAME,cv,BakeryConstants.PRO_COL_CAT_ID+" =? and "+BakeryConstants.PRO_COL_NAME+" =?",arg);
        if(l>0)
        {
            Toast.makeText(this,"price updated",Toast.LENGTH_LONG).show();
        }
    }*/

    /*@Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
