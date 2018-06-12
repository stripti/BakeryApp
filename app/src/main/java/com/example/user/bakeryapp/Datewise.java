package com.example.user.bakeryapp;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;
import java.util.Calendar;

public class Datewise extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    DatePickerDialog dlg;
    Calendar c;
    String date;
    BakeryManager bm;
    SQLiteDatabase sb;
    TextView txtdate;
    OrderBean ob;
    ArrayList<OrderBean> orderlist;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datewise);
        txtdate=(TextView)findViewById(R.id.txtdate);
        lv=(ListView)findViewById(R.id.lv);
        orderlist=new ArrayList<>();
        bm = new BakeryManager(this);
        sb = bm.openDb();
        c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int date=c.get(Calendar.DATE);
        dlg=new DatePickerDialog(this,this,year,month,date);
        dlg.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        c.set(year, month, dayOfMonth);
        date=dayOfMonth+"/"+month+"/"+year;
        //date=c.getTimeInMillis();
        //java.util.Date d=c.getTime();

        txtdate.setText("You chose the date: "+dayOfMonth+"/"+month+"/"+year);

         String[] args = {date};

        Cursor c = sb.query(BakeryConstants.ORDER_TBL_NAME, null, BakeryConstants.ORDER_COL_DATE + " =?", args, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do
            {
                String username = c.getString(c.getColumnIndex(BakeryConstants.ORDER_COL_USERNAME));
                String prodname = c.getString(c.getColumnIndex(BakeryConstants.ORDER_COL_NAME));
                int amount=c.getInt(c.getColumnIndex(BakeryConstants.ORDER_COL_AMOUNT));

                ob = new OrderBean();
                ob.setUname(username);
                ob.setProdname(prodname);
                ob.setAmount(amount);
                orderlist.add(ob);
            } while (c.moveToNext());
            ArrayAdapter<OrderBean> ad=new ArrayAdapter<OrderBean>(this,android.R.layout.simple_list_item_1,orderlist);
            lv.setAdapter(ad);
            c.close();
        }
        else {
            this. finish();
            Toast.makeText(this,"Sorry!! we dont have any order on this date..",Toast.LENGTH_LONG).show();
        }
        }

}
