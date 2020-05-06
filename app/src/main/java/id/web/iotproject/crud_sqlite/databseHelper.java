/*
 * Created by Irfan_NA on 06/05/20 20:29
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 06/05/20 20:28
 */


package id.web.iotproject.crud_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data.db";
    public static final String TABLE_NAME = "data_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "SURENAME";
    public static final String COL4 = "SCORE";

    public databseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURENAME TEXT,SCORE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean tambahData(String nama,String surname, String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,nama);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,score);
        long hasil = db.insert(TABLE_NAME,null ,contentValues);
        if(hasil == -1)
            return false;
        else
            return true;
    }
}
