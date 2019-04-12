package com.example.projet_ajourn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBAdapter {
    private final static String DATABASE_NAME = "Score.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_SCORES = "Score_table";
    private final static String COL_ID   = "ID";
    private final static String COL_NAME   = "NAME";
    private final static String COL_MARK   = "MARK";
    private final static String COL_RESULT   = "RESULT";

    private static final String CREATE_DB =
            "create table " + TABLE_SCORES + " ("
                    + COL_ID + " integer primary key autoincrement, "
                    + COL_NAME + " text not null, "
                    + COL_MARK + " Flaot, "
                    + COL_RESULT + " text not null);";

    private SQLiteDatabase db;
    private MyOpenHelper OpenHelper;

    public MyDBAdapter(Context context){
        OpenHelper = new MyOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public  void open(){ db = OpenHelper.getWritableDatabase();}

    public  void close(){ db.close();}

    public ArrayList<Score> getAllScores(){
        ArrayList<Score> scores = new ArrayList<>();
        Cursor c = db.query(TABLE_SCORES, new String[]{COL_ID,COL_NAME,COL_MARK,COL_RESULT},null,
                null,null,null,COL_MARK);
        c.moveToFirst();
        while (!c.isAfterLast()){
            scores.add(new Score(c.getLong(0),c.getString(1),c.getFloat(2)));
            c.moveToNext();
        }
        c.close();
        return scores;
    }


    public long insertScore(String name, float mark, String result){
        ContentValues contentValue = new ContentValues();
        contentValue.put(COL_NAME,name);
        contentValue.put(COL_MARK,mark);
        contentValue.put(COL_RESULT,result);

        return db.insert(TABLE_SCORES,null,contentValue);
    }

    public int removeScore(long id){return db.delete(TABLE_SCORES,COL_ID+" = "+id,null);}


    /**
     * Private class MyOpenHelper
     */

    private class MyOpenHelper extends SQLiteOpenHelper {
        public MyOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory cursorFactory, int version) {
            super(context, name, cursorFactory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table " + TABLE_SCORES + ";");
            onCreate(db);
        }
    }

}
