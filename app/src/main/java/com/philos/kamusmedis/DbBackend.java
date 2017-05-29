package com.philos.kamusmedis;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by PHILOS on 5/28/2017.
 */

public class DbBackend extends DbObject {
    public DbBackend(Context context){
        super(context);
    }

    public String[] kamusKosakata(){
        String query = "Select * from kamus";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<String> artiKata = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                String kata = cursor.getString(cursor.getColumnIndexOrThrow("kata"));
                artiKata.add(kata);
            } while (cursor.moveToNext());
        }

        cursor.close();

        String[] kamusKosakata = new String[artiKata.size()];
        kamusKosakata = artiKata.toArray(kamusKosakata);

        return kamusKosakata;
    }

    public KamusObject getKamusByKata(String kamusKata){
        KamusObject kamusObject = null;
        String query = "select * from kamus where kata = '" + kamusKata +"'";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                String kata = cursor.getString(cursor.getColumnIndexOrThrow("kata"));
                String arti = cursor.getString(cursor.getColumnIndexOrThrow("arti"));
                kamusObject = new KamusObject(kata, arti);
            }while (cursor.moveToNext());
        }

        cursor.close();
        return kamusObject;
    }

}
