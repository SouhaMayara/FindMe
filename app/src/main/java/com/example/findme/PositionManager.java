package com.example.findme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.findme.PositionHelper.table_pos;

public class PositionManager {

    Context con;
    private SQLiteDatabase mabase;

    public PositionManager(Context con,String fichier,int version){
        this.con=con;
        ouvrir(fichier,version);
    }
    public void ouvrir(String nom_fichier,int version){
        PositionHelper helper=new PositionHelper(con,nom_fichier,null,version);
        mabase=helper.getWritableDatabase();//oncreate /onupgrade
    }

    public long inserer(String num,String lon,String lat)
    {
        ContentValues v =new ContentValues();
        v.put(PositionHelper.col_num,num);
        v.put(PositionHelper.col_lat,lat);
        v.put(PositionHelper.col_long,lon);
        long a=mabase.insert(table_pos,null,v);
        return a;
    }

    public ArrayList<Position> selectionnertout() {
        ArrayList<Position> a=new ArrayList<Position>();
        Cursor cr=mabase.query(PositionHelper.table_pos,
                new String[]{PositionHelper.col_id,
                        PositionHelper.col_num,
                        PositionHelper.col_long,
                        PositionHelper.col_lat,
                        },
                null,
                null,
                PositionHelper.col_num,
                null,
                PositionHelper.col_id);
        cr.moveToFirst();
        while(!cr.isAfterLast())
        {
            int id=cr.getInt(0);
            String n=cr.getString(1);
            String lo=cr.getString(2);
            String la=cr.getString(3);
            Position p=new Position(id,n,lo,la);
            a.add(p);
            cr.moveToNext();//sinon il reste dans la meme position
        }
        //conversion a partir d'un curseur a un tableau a
        return a;
    }

    public int supprimerNum(String numero)
    {
        int a=-1;
        a=mabase.delete(table_pos,
                PositionHelper.col_num+"="+numero,
                null);
        return a;
    }
}
