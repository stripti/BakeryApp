package com.example.user.bakeryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

public class AddCategory extends AppCompatActivity {

    EditText txtcname;
    BakeryManager bm;
    SQLiteDatabase sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        txtcname=(EditText)findViewById(R.id.txtcname);
        bm=new BakeryManager(this);
        sb=bm.openDb();
    }
    public void add(View v)
    {
        String catname=txtcname.getText().toString();
        ContentValues cv=new ContentValues();

        cv.put(BakeryConstants.CAT_COL_NAME,catname);
        long rw= sb.insert(BakeryConstants.CAT_TBL_NAME,null,cv);
        if(rw>0)
        {
           Toast.makeText(this,catname+" is added",Toast.LENGTH_LONG).show();
        }

    }
}
