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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class DeleteProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spprod;
    ProBean pb;
    BakeryManager bm;
    SQLiteDatabase sb;
    ArrayList<ProBean> prolist;
    String pname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);
        spprod = (Spinner) findViewById(R.id.spprod);
        prolist = new ArrayList<>();
        bm = new BakeryManager(this);
        sb = bm.openDb();
        spprod.setOnItemSelectedListener(this);
        fillList();

    }
    public void fillList() {
        Cursor c = sb.query(BakeryConstants.PRO_TBL_NAME, null, null,null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.PRO_COL_NAME));
                pb = new ProBean();
                pb.setPname(name);
                prolist.add(pb);

            } while (c.moveToNext());
            c.close();
        }

        ArrayAdapter<ProBean> ad1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prolist);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprod.setAdapter(ad1);
    }

    public void delete(View v)
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("ALERT!!");
        ad.setIcon(R.drawable.icon);
        ad.setMessage("Are you sure to delete the product??");
        ad.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] arg={pname};
                int rw=sb.delete(BakeryConstants.PRO_TBL_NAME,BakeryConstants.PRO_COL_NAME+" =?",arg);
                if(rw>0)
                {
                    Toast.makeText(DeleteProduct.this,pname+" deleted",Toast.LENGTH_LONG).show();
                    prolist.clear();
                fillList();
                }
            }
        });
        ad.setNegativeButton("No",null);
        AlertDialog a = ad.create();
        a.show();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pname = prolist.get(position).toString();
        //Toast.makeText(this,pname,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
