package com.example.user.bakeryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.bakeryapp.bakery.dbutil.BakeryConstants;
import com.example.user.bakeryapp.bakery.dbutil.BakeryManager;

import java.util.ArrayList;

public class Product extends AppCompatActivity {
    ListView lv;
    ProductBean pb;
    BakeryManager bm;
    SQLiteDatabase sb;
    String name;
    TextView txtprice;
    int cid;
    ArrayList<ProductBean> productlist;
    MyAdapter ad;
    int arr[];
    String[] pname;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productlist = new ArrayList<>();
        lv = (ListView) findViewById(R.id.lv);
        txtprice=(TextView)findViewById(R.id.txtprice);
        bm = new BakeryManager(this);
        sb = bm.openDb();
        ad = new MyAdapter(this, productlist);
        lv.setAdapter(ad);
        arr=ad.pricelist;
        pname=ad.productlist;
        filllist();
    }
    public void filllist() {
        Intent obj = getIntent();
        cid = obj.getIntExtra("msg", 0);
        String[] args = {String.valueOf(cid)};

        Cursor c = sb.query(BakeryConstants.PRO_TBL_NAME, null, BakeryConstants.PRO_COL_CAT_ID + " =?", args, null, null, null, null);
        if (c != null && c.moveToFirst())
        {
            do {
                String name = c.getString(c.getColumnIndex(BakeryConstants.PRO_COL_NAME));
                int price = c.getInt(c.getColumnIndex(BakeryConstants.PRO_COL_PRICE));
                pb = new ProductBean();
                pb.setPname(name);
                pb.setPrice(price);
                productlist.add(pb);

            } while (c.moveToNext());
            c.close();
        }
        else {
           this. finish();
            Toast.makeText(this,"Sorry!! we dont have any product in this category",Toast.LENGTH_LONG).show();
        }
    }
   public void done(View v)
    {
        count=0;
        for (int i=0;i<arr.length;i++)
        {
                count += arr[i];
        }
        txtprice.setText("Total price is:"+String.valueOf(count));
    }
    public void more(View v)
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("ALERT!!");
        ad.setIcon(R.drawable.icon);
        ad.setMessage("Are you sure to place the order??");
        ad.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i=new Intent(Product.this,Form.class);
                i.putExtra("prc",arr);
                i.putExtra("name",pname);
                startActivity(i);

            }
        });
        ad.setNegativeButton("No",null);
        AlertDialog a = ad.create();
        a.show();

    }

}
