package com.example.user.bakeryapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class Message extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    ListView lv;
    String phoneno;
    BakeryManager bm;
    SQLiteDatabase sb;
    UserBean ub;
    EditText txtmsg;
    String msg;
    ArrayList<UserBean> contactlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        /*cl=(ConstraintLayout)findViewById(R.id.cl);
        Animation a= AnimationUtils.loadAnimation(this,R.anim.alpha);
        cl.startAnimation(a);*/
        lv = (ListView) findViewById(R.id.lv);
        contactlist = new ArrayList<>();
        txtmsg=(EditText)findViewById(R.id.txtmsg);
        lv = (ListView) findViewById(R.id.lv);
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
        msg=txtmsg.getText().toString();
        SmsManager sm= SmsManager.getDefault();
        Intent obj=new Intent(this,Message.class);
        PendingIntent pi=PendingIntent.getActivity(this,2,obj,PendingIntent.FLAG_ONE_SHOT);
        sm.sendTextMessage(phoneno,null,msg,pi,null);
        Toast.makeText(this,"message",Toast.LENGTH_LONG).show();
    }
}
