package com.example.user.sqlite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myDB";
    public static final int DATABASE_VERSION = 9;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_PERSON_TABLE  = "CREATE TABLE " + ContentContract.PersonEntry.TABLE_PERSON + " (" +
                ContentContract.PersonEntry.COLUMN_PERSON_ID + " INTEGER PRIMARY KEY , " +
                ContentContract.PersonEntry.COLUMN_PERSON_NAME    + " TEXT, "                +
                ContentContract.PersonEntry.COLUMN_PERSON_SURNAME + " TEXT, "                +
                ContentContract.PersonEntry.COLUMN_PERSON_AGE     + " INTEGER"               +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ContentContract.PersonEntry.TABLE_PERSON);
        this.onCreate(sqLiteDatabase);
    }
}
