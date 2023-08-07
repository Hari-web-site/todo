package com.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.todoapp.Fragment.Home.model.AddTodoModel;

import java.util.ArrayList;


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

    public ArrayList<AddTodoModel> readTodoList(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM "+TABLE_NAME,null);

        ArrayList<AddTodoModel> cursurmodel = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                cursurmodel.add(new AddTodoModel(
                        cursor.getInt(0),
                        cursor.getString(1)));
            } while (cursor.moveToNext());

        }
        cursor.close();

        return cursurmodel;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
