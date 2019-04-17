package com.lijo.signaturelogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CakeDb.db";

    private static final String SQL_CREATE_USERS =
            "CREATE TABLE " + UserContents.UserEntry.TABLE_NAME + " (" +
                    UserContents.UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserContents.UserEntry.COLUMN_USER + " TEXT," +
                    UserContents.UserEntry.COLUMN_PASSWORD + " TEXT)";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean addUser(String name, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContents.UserEntry.COLUMN_USER, name);
        contentValues.put(UserContents.UserEntry.COLUMN_PASSWORD, password);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(UserContents.UserEntry.TABLE_NAME, null, contentValues) != -1;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + UserContents.UserEntry.TABLE_NAME, null);
    }
}
