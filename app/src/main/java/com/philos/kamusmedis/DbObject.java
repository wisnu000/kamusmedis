package com.philos.kamusmedis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by PHILOS on 5/28/2017.
 */

public class DbObject {
    private static KamusDatabase dbHelper;
    private SQLiteDatabase db;

    public DbObject(Context context){
        dbHelper = new KamusDatabase(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection(){
        return this.db;
    }

    public void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }
}
