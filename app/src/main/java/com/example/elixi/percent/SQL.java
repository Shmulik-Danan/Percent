package com.example.elixi.percent;

/**
 * Created by elixi on 01 נובמבר 2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.elixi.percent.MainActivity.arr;


public class SQL extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PRICE";
    public static final String COL_3 = "P1";
    public static final String COL_4 = "P2";
    public static final String COL_5 = "TOTALL";

    public SQL(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRICE TEXT,P1 TEXT,P2 TEXT,TOTALL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String price,String p1,String p2,String totall) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,price);
        contentValues.put(COL_3,p1);
        contentValues.put(COL_4,p2);
        contentValues.put(COL_5,totall);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String price,String p1,String p2,String totall) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,price);
        contentValues.put(COL_3,p1);
        contentValues.put(COL_4,p2);
        contentValues.put(COL_5,totall);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_NAME, "ID = ?",new String[] {id});
        return db.delete(TABLE_NAME, "ID = ?", new String[] { String.valueOf(id) });
    }
    public boolean setarr(){

        Cursor res = getAllData();
        if(res.getCount() != 0) {
            StringBuffer buffer = new StringBuffer();
            arr.clear();
            while (res.moveToNext()) {

                arr.add(new DB(res.getString(1),res.getString(2),res.getString(3),res.getString(4)));

            }
            return true;
        }
        return false;

    }

    public void setdb(ArrayList<DB> arr) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SQL.TABLE_NAME, null, null);
        if(!arr.isEmpty()) {
            // helper is object extends SQLiteOpenHelper
            // db.delete(TABLE_NAME, null, null);


            for (int i = 0; i < arr.size(); i++) {
                insertData(arr.get(i).getPrice(), arr.get(i).getP1(), arr.get(i).getP2(), arr.get(i).getTotall());


            }
        }
    }
}
