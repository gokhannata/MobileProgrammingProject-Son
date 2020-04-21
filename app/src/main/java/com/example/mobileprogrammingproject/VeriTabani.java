package com.example.mobileprogrammingproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VeriTabani extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="kullanicilar";
    private static final int DATABASE_VERSION=2;

    private static final String TABLO_KISILER="kisiler";
    private static final String ROW_ID="id";
    private static final String ROW_USERNAME="username";
    private static final String ROW_PASSWORD="password";
    private static final String ROW_IMG="image";

    public VeriTabani(Context context) {
        //super(context,new File(Environment.getExternalStorageDirectory(),DATABASE_NAME).toString(),null,DATABASE_VERSION);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //super(context, String.valueOf(context.getDatabasePath(DATABASE_NAME)), null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_KISILER + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_USERNAME + " TEXT NOT NULL, "
                + ROW_PASSWORD + " TEXT NOT NULL, "
                + ROW_IMG + " INTEGER NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_KISILER);
        onCreate(db);
    }
    public void kullaniciEkle(Kullanicilar kullanici){
        SQLiteDatabase db=this.getWritableDatabase();
        try{
            ContentValues degerler=new ContentValues();
            degerler.put(ROW_USERNAME,kullanici.getUsername());
            degerler.put(ROW_PASSWORD,kullanici.getPassword());
            degerler.put(ROW_IMG,kullanici.getImgSrc());
            db.insert(TABLO_KISILER,null,degerler);
        }catch (Exception e){

        }
        db.close();
    }
    public ArrayList<Kullanicilar> kullanicilariGetir(){
        ArrayList<Kullanicilar> kullaniciListesi=new ArrayList<>();
        String query="Select * From "+TABLO_KISILER;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        Kullanicilar kullanicilar=null;
        if(cursor.moveToFirst()){
            do {
                kullanicilar=new Kullanicilar();
                kullanicilar.setId(Integer.parseInt(cursor.getString(0)));
                kullanicilar.setUsername(cursor.getString(1));
                kullanicilar.setPassword(cursor.getString(2));
                kullanicilar.setImgSrc(Integer.parseInt(cursor.getString(3)));
                kullaniciListesi.add(kullanicilar);
            }while (cursor.moveToNext());
        }
        return kullaniciListesi;
    }
    public boolean login(String username,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * From kisiler where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }
}
