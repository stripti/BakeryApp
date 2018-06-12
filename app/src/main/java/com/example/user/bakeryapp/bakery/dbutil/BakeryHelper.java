package com.example.user.bakeryapp.bakery.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by User on 7/14/2017.
 */

public class BakeryHelper extends SQLiteOpenHelper{
    Context context;
    public BakeryHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BakeryConstants.CAT_TBL_QUERY);
        //Toast.makeText(context," category created",Toast.LENGTH_LONG).show();
        db.execSQL(BakeryConstants.PRO_TBL_QUERY);
       // Toast.makeText(context,"product created",Toast.LENGTH_LONG).show();
        db.execSQL(BakeryConstants.USER_TBL_QUERY);
       // Toast.makeText(context,"user created",Toast.LENGTH_LONG).show();
        db.execSQL(BakeryConstants.ORDER_TBL_QUERY);
       // Toast.makeText(context,"order created",Toast.LENGTH_LONG).show();



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
