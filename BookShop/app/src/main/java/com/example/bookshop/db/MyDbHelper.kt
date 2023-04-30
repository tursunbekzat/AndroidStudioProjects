package com.example.bookshop.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context):SQLiteOpenHelper(context, MyDbNameClass.DATABASE_NAME, null, MyDbNameClass.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MyDbNameClass.Create_Table1)
//        db?.execSQL(MyDbNameClass.Create_Table2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(MyDbNameClass.SQL_DELETE_TABLE1)
//        db.execSQL(MyDbNameClass.SQL_DELETE_TABLE2)
        onCreate(db)
    }
}