package com.example.beanshop;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DatabaseName = "database.db";
    public String currentUser;

    public DBHelper(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table usersList(username TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table bought(coffee TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int x, int y) {
        MyDatabase.execSQL("drop Table if exists usersList");
        MyDatabase.execSQL("drop Table if exists bought");
        onCreate(MyDatabase);
    }

    public Boolean insertData(String user_name, String user_password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(), borrowValues = new ContentValues(), completeValues = new ContentValues();

        contentValues.put("username", user_name);
        contentValues.put("password", user_password);

        long result = MyDatabase.insert("usersList", null, contentValues);
        Log.d("TAG", "Insertion result: " + result);

        if (result == -1 )
            return false;
        else {
            currentUser = user_name;
            return true;
        }
    }

    public Boolean insertcoffeeData(String coffee) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(), borrowValues = new ContentValues(), completeValues = new ContentValues();

        contentValues.put("coffee", coffee);

        long result = MyDatabase.insert("bought", null, contentValues);

        if (result == -1 )
            return false;
        else {

            return true;
        }
    }

    public Boolean editData(String newName, String newPassword, String oldName, String oldPassword) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", newName);
        contentValues.put("password", newPassword);

        long result = MyDatabase.update("usersList", contentValues, "username = oldName", new String[]{oldName});
        if (result == -1)
            return false;
        else {
            long result2 = MyDatabase.update("usersList", contentValues, "password = oldPassword", new String[]{oldPassword});
            if(result2 == -1)
                return false;
            else
                return true;
        }
    }

    public Boolean usernameValidation(String username) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();

        Cursor cursor = MyDatabase.rawQuery("Select * from usersList where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from usersList", null);
        return cursor;
    }



    public Boolean passwordValidation(String username, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        String query = "Select * from usersList where username = '" + username + "' and password = '" + password + "'";
        Log.d("TAG", "query: " + query);

        Cursor cursor = MyDatabase.rawQuery(query, null);
        Log.d("TAG", "Number of rows returned: " + cursor.getCount());


        if (cursor.getCount() > 0) {
            return true;
        } else {
            currentUser = username;
            return false;
        }
    }

}
