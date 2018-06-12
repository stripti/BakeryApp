package com.example.user.bakeryapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class Calling extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    String phoneno;
    BakeryManager bm;
    SQLiteDatabase sb;
    UserBean ub;
    ArrayList<UserBean> contactlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        lv = (ListView) findViewById(R.id.list);
        contactlist = new ArrayList<>();
        bm = new BakeryManager(this);
        sb = bm.openDb();
        fillList();

    }

    public void fillList() {
        Cursor c = sb.query(BakeryConstants.USER_TBL_NAME, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.USER_COL_NAME));
                String phone = c.getString(c.getColumnIndex(BakeryConstants.USER_COL_PHONE));

                ub = new UserBean();
                ub.setUsername(name);
                ub.setPhone(phone);
                contactlist.add(ub);

            } while (c.moveToNext());
            ArrayAdapter ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactlist);
            lv.setAdapter(ad);
            lv.setOnItemClickListener(this);
            c.close();
        }
        else {
            this. finish();
            Toast.makeText(this,"Sorry!! we dont have any users ",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserBean c1 = contactlist.get(position);
        phoneno = c1.getPhone();
        Intent i = new Intent(Intent.ACTION_CALL);
        Uri u = Uri.parse("tel:" + phoneno);
        i.setData(u);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
           return;
        }
        startActivity(i);
    }
}
