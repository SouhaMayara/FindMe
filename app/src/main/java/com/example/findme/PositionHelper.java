package com.example.findme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class PositionHelper extends SQLiteOpenHelper {
   /* String req="create table("+
        "id integer primary key autoincrement,"+
        "numero text not null,"+
        "logitude text not null,"+
        "latitude text not null)";*/
    public static  String nom_fichier="mespositions.db";
    public static int version_base=1;


   public static final String table_pos = "position";
    public static final String col_id = "Id";
    public static final String col_num = "Numero";
    public static final String col_long = "Longitude";
    public static final String col_lat = "Latitude";

    String req = "create table "+table_pos+"( " +
            col_id      +" integer primary key autoincrement," +
            col_num     +" text not null," +
            col_long    +" text not null," +
            col_lat     +" text not null)";

    public PositionHelper(@Nullable Context context,
                          @Nullable String name,//nom du fichier *.db
                          @Nullable SQLiteDatabase.CursorFactory factory,//null
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //lors de la creation du fichier ==>creation des tables
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //lors de la modification de la version
        db.execSQL("drop table "+table_pos);
        onCreate(db);
    }
}
