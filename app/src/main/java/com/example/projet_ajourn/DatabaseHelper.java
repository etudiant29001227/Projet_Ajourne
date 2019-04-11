package com.example.projet_ajourn;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "Score.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME   = "Score_table";
    private final static String COL_1   = "ID";
    private final static String COL_2   = "NAME";
    private final static String COL_3   = "MARK";
    private final static String COL_4   = "RESULT";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTERGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, MARK TEXT, RESULT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name, String mark, String result){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,mark);
        contentValues.put(COL_4,result);

        return db.insert(TABLE_NAME,null,contentValues)!=-1;
    }

}
