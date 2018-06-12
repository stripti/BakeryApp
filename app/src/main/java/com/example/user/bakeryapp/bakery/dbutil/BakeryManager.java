package com.example.user.bakeryapp.bakery.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by User on 7/14/2017.
 */

public class BakeryManager {
    Context context;
    BakeryHelper bh;
    SQLiteDatabase sb;
    public BakeryManager(Context context)
    {
        this.context=context;
        bh=new BakeryHelper(context,BakeryConstants.DB_NAME,null,BakeryConstants.DB_VERSION);

    }

    public SQLiteDatabase openDb()
    {
        sb=bh.getWritableDatabase();
        return sb;
    }
    public void closeDb()
    {
        bh.close();
    }

}
