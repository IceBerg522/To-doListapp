package com.example.student.to_dolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Student on 30/05/2016.
 */
public class ATDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "list";
    private static final int DB_VERSION = 1;


    public ATDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE LIST (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TITLE TEXT, " +
                "CONTENT TEXT, " +
                "TAGS TEXT);");
    }

    public void insertElement(SQLiteDatabase db, ContentValues newContent) {

        db.insert("LIST", null, newContent);

    }
}

