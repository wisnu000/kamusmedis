package com.philos.kamusmedis;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by PHILOS on 5/28/2017.
 */

public class KamusDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAMES = "kamus";
    private static final int DATABASE_VERSION = 1;
    public KamusDatabase( Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
}
