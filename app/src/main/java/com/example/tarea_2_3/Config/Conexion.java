package com.example.tarea_2_3.Config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.tarea_2_3.Clases.Photograph;
import com.example.tarea_2_3.Clases.Transacs;

import java.util.ArrayList;

public class Conexion extends SQLiteOpenHelper {

    public Conexion(Context context, String dbName, SQLiteDatabase.CursorFactory fact, int version){
        super(context, dbName, fact, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Transacs.createTblFoto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Transacs.dropFotos);
        onCreate(db);
    }

    public boolean SaveData(byte[] img, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Transacs.img, img);
        cv.put(Transacs.desc, desc);

        long ins =  db.insert(Transacs.tblName, Transacs.id, cv);
        if(ins == -1)return false;
        else return true;
    }

    public ArrayList<Photograph> getFoto(ArrayList<Photograph> _foto){
        SQLiteDatabase db = this.getReadableDatabase();

        int test  =0;
        Photograph photo = null;
        _foto = new ArrayList<Photograph>();
        Log.d("FOO", "select * from " + Transacs.tblName);
        Cursor cursor = db.rawQuery("select * from " + Transacs.tblName, null);

        while (cursor.moveToNext()){
            test++;
            System.out.println("entro");
            photo = new Photograph();
            photo.setId(cursor.getInt(0));
            System.out.println("ID"+cursor.getInt(0));
            byte[] img =cursor.getBlob(1);
            photo.setImg(BitmapFactory.decodeByteArray(img, 0, img.length));
            photo.setDesc("");
            _foto.add(photo);
        }

        if(test==0) System.out.println("no");
        cursor.close();
        return  _foto;
    }
}
