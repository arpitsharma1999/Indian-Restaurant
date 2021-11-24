package com.example.p1sqliteindianrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHealper extends SQLiteOpenHelper {

    final static String TABLE_NAME = "USER";
    final static String col1="Number";
    final static String col2="Password";
    final  static String col3="Name";
    final static  String col4="Email";
    final static  String col5="Admin";
    public DatabaseHealper(Context context)
    {
        super(context,TABLE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatrTable ="CREATE TABLE "+ TABLE_NAME + "(Number TEXT PRIMARY KEY, Password TEXT , Name TEXT , Email TEXT,Admin INTEGER )";
        db.execSQL(creatrTable);
        ContentValues contentValues =new ContentValues();
        contentValues.put(col1,"9456207267");
        contentValues.put(col2,"123");
        contentValues.put(col3,"Admin");
        contentValues.put(col4,"admin.123@amazon.in");
        contentValues.put(col5,1);
        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String name, String num, String pass, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(col1,num);
        contentValues.put(col2,pass);
        contentValues.put(col3,name);
        contentValues.put(col4,email);
        contentValues.put(col5,0);

        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return result!=-1;
    }
    public  boolean isNumExists(String num)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT count(*) FROM   " + TABLE_NAME +"  WHERE Number  =  "+num,null);
        c.moveToFirst();
        int count= c.getInt(0);
        db.close();
        return count==1;
    }
    public boolean isAValidPassWithNum(String num , String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT count(*) FROM "+ TABLE_NAME +" WHERE Number = '"+num +"' AND Password = '"+pass+"'", null);
        c.moveToFirst();
        int count= c.getInt(0);
        db.close();
        return count==1;
    }
    public boolean isAdmin(String num)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT count(*) FROM "+ TABLE_NAME +" WHERE Number = '"+num +"' AND Admin = 1",  null);
        c.moveToFirst();
        int count= c.getInt(0);
        db.close();
        return count==1;
    }
    public List<String> getData()
    {
        List<String> data  = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c= db.rawQuery("SELECT Name , Number FROM USER",null  );
        while(c.moveToNext())
        {
            String tmp = "Name-> "+c.getString(0)+"  Number-> "+c.getString(1);
            data.add(tmp);
        }
        db.close();
        return data;
    }
    public boolean deleteRow(String num)
    {
        if(isAdmin(num))
            return false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE Number ="+num);
        db.close();
        final boolean b = !isNumExists(num);
        return b;
    }
    public void updateName(String num, String name)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE "+ TABLE_NAME +" SET Name = '"+ name+"'  WHERE Number = '"+ num+"'";

        db.execSQL(strSQL);
        db.close();
    }
}
