package com.example.user.bakeryapp;

/**
 * Created by USER on 16-07-2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends BaseAdapter

{
    private List<ProductBean> mylist;
    public int[] pricelist=new int[50];
    public String[] productlist=new String[50];
    String name=null;
    int price = 0;
    private LayoutInflater lf = null;
    Context ctx = null;

    public MyAdapter(Activity activity, ArrayList<ProductBean> mylist) {
        ctx = activity.getApplicationContext();
        this.mylist = mylist;
        lf = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       // Toast.makeText(ctx, "view created", Toast.LENGTH_LONG).show();
        if (convertView == null) {
            convertView = lf.inflate(R.layout.mycustom, parent, false);
        }
        CheckBox ch = (CheckBox) convertView.findViewById(R.id.ch1);


        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        final ProductBean[] f = {mylist.get(position)};
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f[0] = mylist.get(position);
                price = f[0].getPrice();
                name=f[0].getPname();
                pricelist[position]= price;
                productlist[position]=name;
               // Toast.makeText(ctx,"hello"+price, Toast.LENGTH_LONG).show();
            }
        });
        tv.setText(f[0].getPname() + "            " + f[0].getPrice());
        final Typeface tvfont=Typeface.MONOSPACE;
        tv.setTypeface(tvfont);
        tv.setTextColor(Color.BLUE);
        return convertView;


    }
}
