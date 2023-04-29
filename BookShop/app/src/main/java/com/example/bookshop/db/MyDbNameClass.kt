package com.example.bookshop.db

//import android.os.Parcelable.Creator
import android.provider.BaseColumns
//import com.example.bookshop.db.MyDbNameClass.COLUMN_ID

object MyDbNameClass{
    const val TABLE_NAME1 = "users"
    const val COLUMN_NAME_LOGIN = "login"
    const val COLUMN_NAME_PASSWORD = "password"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_SURNAME = "surname"

    const val TABLE_NAME2 = "books"
    const val COLUMN_TITLE = "title"
    const val COLUMN_AUTHOR = "author"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_COST = "cost"

    const val DATABASE_VERSION = 5
    const val DATABASE_NAME = "BookShopDb.db"

    // Client Table    
    const val Create_Table1 = "CREATE TABLE IF NOT EXISTS $TABLE_NAME1 (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$COLUMN_NAME_NAME TEXT, " +
            "$COLUMN_NAME_SURNAME TEXT, " +
            "$COLUMN_NAME_LOGIN TEXT, " +
            "$COLUMN_NAME_PASSWORD TEXT)"
    const val SQL_DELETE_TABLE1 = "DROP TABLE IF EXISTS $TABLE_NAME1"

    // Books Table    
    const val Create_Table2 = 
        "CREATE TABLE IF NOT EXISTS " +
            "$TABLE_NAME2 (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$COLUMN_TITLE TEXT, " +
            "$COLUMN_AUTHOR TEXT, " +
            "$COLUMN_DESCRIPTION TEXT, " +
            "$COLUMN_COST REAL)"
    const val SQL_DELETE_TABLE2 = "DROP TABLE IF EXISTS $TABLE_NAME2"

}