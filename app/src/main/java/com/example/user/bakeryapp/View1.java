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

public class View1 extends AppCompatActivity {

    ListView lv;
    BakeryManager sm;
    SQLiteDatabase sb;
    AllProductBean p;
    ArrayList<AllProductBean> alllist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);
        lv=(ListView)findViewById(R.id.lv);
        sm=new BakeryManager(this);
        sb=sm.openDb();
        alllist=new ArrayList<>();
        fillList();

    }
    public void fillList() {
        Cursor c = sb.query(BakeryConstants.PRO_TBL_NAME, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
               // int cid = c.getInt(c.getColumnIndex(BakeryConstants.PRO_COL_CAT_ID));
                int pid = c.getInt(c.getColumnIndex(BakeryConstants.PRO_COL_ID));
                String pname = c.getString(c.getColumnIndex(BakeryConstants.PRO_COL_NAME));
                int price = c.getInt(c.getColumnIndex(BakeryConstants.PRO_COL_PRICE));

                p = new AllProductBean();
                p.setPid(pid);
                p.setPname(pname);
                p.setPrice(price);
                alllist.add(p);

            } while (c.moveToNext());
            ArrayAdapter<AllProductBean> ad=new ArrayAdapter<AllProductBean>(this,android.R.layout.simple_list_item_1,alllist);
            lv.setAdapter(ad);
            c.close();
        }
        else {
            this. finish();
            Toast.makeText(this,"Sorry!! we dont have any products in shop ...",Toast.LENGTH_LONG).show();
        }
    }
    }
