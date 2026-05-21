package com.example.mymachine.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="market.db";//名字
    private static final int VERSION=1;//版本号
    public static SQLiteDatabase db=null;//数据库
    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创建数据库表格，第一次
        db.execSQL("drop table if exists products");
        db.execSQL("create table if not exists products"+"(pid varchar primary key,"+
                "pname varchar(20),"+
                "image varchar(30),"+
                "pnum varchar(10),"+
                "snum varchar(10),"+
                "price varchar(10))");
        db.execSQL("INSERT INTO products VALUES('1','coco','0','0','0',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }
}
