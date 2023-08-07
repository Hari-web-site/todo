package com.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    final static String TABLE_NAME = "TodoDatabase";
    final static int VERSION = 1;
    final static String ID = "id";
    final static String TITLE = "title";


    public DBHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = " Create table "+TABLE_NAME+"("
                + ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE +" TEXT) ";

        sqLiteDatabase.execSQL(query);
    }

    public void addTodoList(String title){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,title);
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
