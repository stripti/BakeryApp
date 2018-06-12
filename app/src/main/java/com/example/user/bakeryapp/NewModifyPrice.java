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
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class NewModifyPrice extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spprod;
    EditText txtprice;
    TextView price;
    ProBean pb;
    BakeryManager bm;
    SQLiteDatabase sb;
    ArrayList<ProBean> prolist;
    String pname;
    int price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_modify_price);
        spprod = (Spinner) findViewById(R.id.spprod);
        txtprice = (EditText) findViewById(R.id.txtprice);
        price = (TextView)findViewById(R.id.price);
        prolist = new ArrayList<>();
        bm = new BakeryManager(this);
        sb = bm.openDb();
        spprod.setOnItemSelectedListener(this);
        fillList();

    }
    public void fillList() {
        Cursor c = sb.query(BakeryConstants.PRO_TBL_NAME, null, null,null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.PRO_COL_NAME));
                pb = new ProBean();
                pb.setPname(name);
                prolist.add(pb);

            } while (c.moveToNext());
            c.close();
        }

        ArrayAdapter<ProBean> ad1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prolist);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprod.setAdapter(ad1);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pname = prolist.get(position).toString();
       // Toast.makeText(this,pname,Toast.LENGTH_LONG).show();
        String[] arg={pname};
            Cursor c2 = sb.query(BakeryConstants.PRO_TBL_NAME, null, BakeryConstants.PRO_COL_NAME + " =?", arg, null, null, null);
            if (c2 != null && c2.moveToFirst())
            {
                price1 = c2.getInt(c2.getColumnIndex(BakeryConstants.PRO_COL_PRICE));

            }
            c2.close();

        price.setText("Earlier price is: "+price1);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void change(View v)
    {
        int fprice=Integer.parseInt(txtprice.getText().toString());
        String[] arg={pname};
        ContentValues cv=new ContentValues();
        cv.put(BakeryConstants.PRO_COL_PRICE,fprice);
        long l=sb.update(BakeryConstants.PRO_TBL_NAME,cv,BakeryConstants.PRO_COL_NAME+" =?",arg);
        if(l>0)
        {
            Toast.makeText(this,"Price Updated",Toast.LENGTH_LONG).show();
        }
    }
}
