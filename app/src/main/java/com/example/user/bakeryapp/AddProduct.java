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
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText txtpname,txtprice;
    Spinner sp;
    ArrayList<CategoryBean> catlist;
    CategoryBean cb;
    BakeryManager bm;
    SQLiteDatabase sb;
    int id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        sp=(Spinner)findViewById(R.id.sp);
        txtpname=(EditText) findViewById(R.id.txtpname);
        txtprice=(EditText) findViewById(R.id.txtprice);
        catlist=new ArrayList<>();
        bm=new BakeryManager(this);
        sb=bm.openDb();
        fillList();
        ArrayAdapter<CategoryBean> ad=new ArrayAdapter<CategoryBean>(this,android.R.layout.simple_list_item_1,catlist);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);
    }
    public void fillList()
    {
        Cursor c=sb.query(BakeryConstants.CAT_TBL_NAME,null,null,null,null,null,null);
        if(c!=null && c.moveToFirst())
        {
            do{
                String name=c.getString(c.getColumnIndex(BakeryConstants.CAT_COL_NAME));

                cb=new CategoryBean();
                cb.setCname(name);
                catlist.add(cb);

            }while (c.moveToNext());
            c.close();
        }
    }
    public void addproduct(View v)
    {
        String pname=txtpname.getText().toString();
        int price=Integer.parseInt(txtprice.getText().toString());
        String[] args={name};
        Cursor c=sb.query(BakeryConstants.CAT_TBL_NAME,null,BakeryConstants.CAT_COL_NAME+" =?",args,null,null,null,null);

        if(c!=null && c.moveToFirst())
        {
            id=c.getInt(c.getColumnIndex(BakeryConstants.CAT_COL_ID));
        }

        ContentValues cv=new ContentValues();
        cv.put(BakeryConstants.PRO_COL_CAT_ID,id);
        cv.put(BakeryConstants.PRO_COL_NAME,pname);
        cv.put(BakeryConstants.PRO_COL_PRICE,price);

        long rw= sb.insert(BakeryConstants.PRO_TBL_NAME,null,cv);
        if(rw>0)
        {
            Toast.makeText(this,pname+" is added",Toast.LENGTH_LONG).show();
        }


        c.close();



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        name=sp.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
