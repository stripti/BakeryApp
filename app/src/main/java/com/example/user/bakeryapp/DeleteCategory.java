package com.example.user.bakeryapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class DeleteCategory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spcat;
    CategoryBean cb;
    BakeryManager bm;
    SQLiteDatabase sb;
    ArrayList<CategoryBean> catlist;
    int cid;
    String cname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);
        spcat = (Spinner) findViewById(R.id.spcat);
        catlist = new ArrayList<>();
        bm = new BakeryManager(this);
        sb = bm.openDb();
        spcat.setOnItemSelectedListener(this);
        fillList();

    }
    public void fillList() {
        Cursor c = sb.query(BakeryConstants.CAT_TBL_NAME, null, null,null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.CAT_COL_NAME));
                cb = new CategoryBean();
                cb.setCname(name);
                catlist.add(cb);

            } while (c.moveToNext());
            c.close();
        }

        ArrayAdapter<CategoryBean> ad1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, catlist);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spcat.setAdapter(ad1);
    }
    public void deletecat(View v)
    {
        final String[] arg={cname};
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setIcon(R.drawable.icon);
        ad.setTitle("ALERT!!");
        ad.setMessage("Are you sure to delete the category??");
        ad.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Cursor c = sb.query(BakeryConstants.CAT_TBL_NAME, null, BakeryConstants.CAT_COL_NAME + " =?", arg, null, null, null);
                if (c != null && c.moveToFirst())
                {
                    cid = c.getInt(c.getColumnIndex(BakeryConstants.CAT_COL_ID));

                }
                int rw=sb.delete(BakeryConstants.CAT_TBL_NAME,BakeryConstants.CAT_COL_NAME+" =?",arg);
                if(rw>0)
                {
                    Toast.makeText(DeleteCategory.this,cname+" deleted",Toast.LENGTH_LONG).show();
                    catlist.clear();
                    fillList();
                }
                deletepro();
                c.close();

            }
        });
        ad.setNegativeButton("No",null);
        AlertDialog a = ad.create();
        a.show();
    }
    public void deletepro()
    {
        final String[] args={String.valueOf(cid)};
        int rw1=sb.delete(BakeryConstants.PRO_TBL_NAME,BakeryConstants.PRO_COL_CAT_ID+" =?",args);
        if(rw1>0)
        {
            Toast.makeText(DeleteCategory.this,"products deleted",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cname = catlist.get(position).toString();
       // Toast.makeText(this,cname,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
