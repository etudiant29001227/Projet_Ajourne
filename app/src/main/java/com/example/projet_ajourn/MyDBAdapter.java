package com.example.projet_ajourn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBAdapter {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Scores_database.db";

    private static final String TABLE_SCORES = "table_scores";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_GRADE= "grade";
    public static final String COL_RESULT = "result";

    private static final String CREATE_DB =
            "create table " + TABLE_SCORES + " ("
                    + COL_ID + " integer primary key autoincrement, "
                    + COL_NAME + " text not null, "
                    + COL_GRADE+ " Flaot, "
                    + COL_RESULT+ " text not null);";

    private SQLiteDatabase mDB;
    private MyOpenHelper mOpenHelper;

    public MyDBAdapter(Context context) {
        mOpenHelper = new MyOpenHelper(context, DB_NAME,null, DB_VERSION);
    }

    public void open() {
        mDB = mOpenHelper.getWritableDatabase();
    }

    public void close() {
        mDB.close();
    }

    public Score getScore(long id) throws SQLException {
        Score score = null;

        Cursor c = mDB.query(TABLE_SCORES,
                new String [] {COL_ID, COL_NAME, COL_GRADE,COL_RESULT },
                COL_ID + " = " + id, null, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            score = new Score(c.getLong(0), c.getString(1), c.getFloat(2));
        }
        c.close();

        return score;
    }

    public ArrayList<Score> getAllScore() {
        ArrayList<Score> scores = new ArrayList<Score>();

        Cursor c =  mDB.query(TABLE_SCORES,
                new String[] {COL_ID, COL_NAME, COL_GRADE,COL_RESULT },
                null, null, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            scores.add(
                    new Score(c.getLong(0), c.getString(1), c.getFloat(2)));
            c.moveToNext();
        }
        c.close();

        return scores;
    }

    public long insertTache(String name,float grade ) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_GRADE, grade);
        return mDB.insert(TABLE_SCORES, null, values);
    }

    public int updateTache(long id, String name, float grade) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_GRADE, grade);
        return mDB.update(TABLE_SCORES, values, COL_ID + "=" + id, null);
    }

    public int removeTache(long id) {
        return mDB.delete(TABLE_SCORES, COL_ID + " = " + id, null);
    }

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
