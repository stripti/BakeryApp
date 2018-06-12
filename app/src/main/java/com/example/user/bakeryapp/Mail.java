package com.example.user.bakeryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class Mail extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lv;
    BakeryManager bm;
    SQLiteDatabase sb;
    UsersBean ub;
    ArrayList<UsersBean> contactlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        lv = (ListView) findViewById(R.id.lvs);
        contactlist = new ArrayList<>();
        bm = new BakeryManager(this);
        sb = bm.openDb();

        filllist();
    }
    public void filllist()
    {
        Cursor c = sb.query(BakeryConstants.USER_TBL_NAME, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.USER_COL_NAME));
                String email = c.getString(c.getColumnIndex(BakeryConstants.USER_COL_EMAIL));
                ub = new UsersBean();
                ub.setUsername(name);
                ub.setEmail(email);
                contactlist.add(ub);
            } while (c.moveToNext());
            ArrayAdapter ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactlist);
            lv.setAdapter(ad);
            lv.setOnItemClickListener( this);
            c.close();
        }
        else {
            this. finish();
            Toast.makeText(this,"Sorry!! we dont have any users ",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UsersBean c1 = contactlist.get(position);
        String mail = c1.getEmail();
        String[] email={mail};
        //Toast.makeText(this,"hello"+mail,Toast.LENGTH_LONG).show();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,email);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }
}

