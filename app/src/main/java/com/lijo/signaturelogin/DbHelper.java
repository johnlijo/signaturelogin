package com.lijo.signaturelogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserDb.db";
    public static final String TABLE_NAME = "users";
    public static final String KEY_ID = "id";
    public static final String COLUMN_USER= "username";
    public static final String COLUMN_PASSWORD = "paassword";

    private static final String SQL_CREATE_USERS =
            "CREATE TABLE " +TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_USER + " TEXT UNIQUE," +
                    COLUMN_PASSWORD + " TEXT)";


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
        contentValues.put(COLUMN_USER, name);
        contentValues.put(COLUMN_PASSWORD, password);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean isUserExists(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{KEY_ID, COLUMN_USER, COLUMN_USER, COLUMN_PASSWORD},
                COLUMN_USER + "=?",
                new String[]{user},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }

        return false;
    }

    public boolean logUser(String user, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                new String[]{KEY_ID},                    //columns to return
                COLUMN_USER + " = ?" + " AND " + COLUMN_PASSWORD + " = ?",                  //columns for the WHERE clause
                new String[]{user, password},              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
