package com.example.user.bakeryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewOrder extends AppCompatActivity {

    ListView lv;
    BakeryManager sm;
    SQLiteDatabase sb;
    OrderBean o;
    ArrayList<OrderBean> orderlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        lv=(ListView)findViewById(R.id.lv);
        sm=new BakeryManager(this);
        sb=sm.openDb();
        orderlist=new ArrayList<>();
        fillList();
    }
    public void fillList() {
        Cursor c = sb.query(BakeryConstants.ORDER_TBL_NAME, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String username = c.getString(c.getColumnIndex(BakeryConstants.ORDER_COL_USERNAME));
                String prodname = c.getString(c.getColumnIndex(BakeryConstants.ORDER_COL_NAME));
                int amount=c.getInt(c.getColumnIndex(BakeryConstants.ORDER_COL_AMOUNT));
                long d=c.getLong(c.getColumnIndex(BakeryConstants.ORDER_COL_DATE));
                Calendar cal=Calendar.getInstance();
                cal.setTimeInMillis(d);
                java.util.Date date=cal.getTime();
                String odate= String.valueOf(date);
                o = new OrderBean();
                o.setUname(username);
                o.setProdname(prodname);
                o.setAmount(amount);
                o.setOdate(odate);
                orderlist.add(o);


            } while (c.moveToNext());
            ArrayAdapter<OrderBean> ad = new ArrayAdapter<OrderBean>(this, android.R.layout.simple_list_item_1, orderlist);
            lv.setAdapter(ad);
            c.close();
        }
        else {
            this. finish();
            Toast.makeText(this,"Order has not been placed yet!!",Toast.LENGTH_LONG).show();
        }
    }
}
