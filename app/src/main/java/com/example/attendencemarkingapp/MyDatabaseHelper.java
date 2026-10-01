package com.example.attendencemarkingapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // Database info
    private static final String DATABASE_NAME = "login_db";
    private static final int DATABASE_VERSION = 1;

    // Table info
     static final String TABLE_USERS = "users";
     static final String COL_ID = "id";
     static final String COL_PASSWORD = "password";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_ID + " TEXT PRIMARY KEY, " +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createTable);

        // Optional: insert one default user
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, "admin");
        cv.put(COL_PASSWORD, "1234");
        db.insert(TABLE_USERS, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

}
