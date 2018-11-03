package com.example.danishali.assignment01;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

class TestDBOpenHepler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";
    public static final String COL3 = "ITEM2";


    public TestDBOpenHepler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ITEM1 TEXT,ITEM2 TEXT UNIQUE)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item2,String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        contentValues.put(COL3,item2);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updateData(String item1,String existingvalue) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        SQLiteDatabase db = this.getWritableDatabase();
       db.update(TABLE_NAME,contentValues,"item1 = ?", new String[]{existingvalue});
       return true;
    }

    public boolean removeData(String item) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] conditions = {item};


         return db.delete(TABLE_NAME,COL3+"=?",conditions) > 0;


    }
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public String getitem(String where){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT *  FROM "+ TABLE_NAME+" WHERE "+ COL3 +" = \""+where + "\"", null);
        data.moveToFirst();
        String s1 = data.getString(1);
        return s1;
    }

    public String  totalrecords(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return Long.toString(count);


    }

    public void clearDatabase(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        db.execSQL(clearDBQuery);
        totalrecords();

    }


}
