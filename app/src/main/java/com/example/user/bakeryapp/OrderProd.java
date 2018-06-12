package com.example.user.bakeryapp;

import android.content.Intent;
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

public class OrderProd extends AppCompatActivity {

    ListView lv;
    OrderProdwise op;
    ArrayList<OrderProdwise> orderlist;
    BakeryManager bm;
    SQLiteDatabase sb;
    String pname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_prod);
        lv=(ListView)findViewById(R.id.lv);
        orderlist=new ArrayList<>();
        bm = new BakeryManager(this);
        sb = bm.openDb();
        Intent obj=getIntent();
        pname=obj.getStringExtra("msg");
        fillList();
    }
    public void fillList()
    {
        String[] args = {String.valueOf(pname)};
        /*op1 = new OrderProdwise();
        op1.setUname("username");
        op1.setAmount("amount");
        op1.setOdate("odate");
        orderlist.add(op1);*/
        Cursor c = sb.query(BakeryConstants.ORDER_TBL_NAME, null, BakeryConstants.ORDER_COL_NAME + " =?", args, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do
            {
                String username = c.getString(c.getColumnIndex(BakeryConstants.ORDER_COL_USERNAME));
                int amount=c.getInt(c.getColumnIndex(BakeryConstants.ORDER_COL_AMOUNT));
                String odate=c.getString(c.getColumnIndex(BakeryConstants.ORDER_COL_DATE));
               /* Calendar cal=Calendar.getInstance();
                cal.setTimeInMillis(date);
                java.util.Date d=cal.getTime();
                String odate= String.valueOf(d);*/
                op = new OrderProdwise();
                op.setUname(username);
                op.setAmount(String.valueOf(amount));
                op.setOdate(odate);

                orderlist.add(op);
            } while (c.moveToNext());
            ArrayAdapter<OrderProdwise> ad=new ArrayAdapter<OrderProdwise>(this,android.R.layout.simple_list_item_1,orderlist);
            lv.setAdapter(ad);
            c.close();
        }
        else {
            this. finish();
            Toast.makeText(this,"Sorry!! we dont have any order for this product",Toast.LENGTH_LONG).show();
        }
    }
}
