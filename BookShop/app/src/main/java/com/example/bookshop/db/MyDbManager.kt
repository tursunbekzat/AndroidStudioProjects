package com.example.bookshop.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.example.bookshop.Book


class MyDbManager(val context: Context) {

    private val myDbHelper = MyDbHelper(context)
    private var db:SQLiteDatabase? = null
//    private val books = mutableListOf<MutableList<String>>()


    fun openDb(){
        db = myDbHelper.writableDatabase
    }

    /*
//    fun exists(name:String): Boolean{
//        val dbFile = context.getDatabasePath(name)
//        return dbFile.exists()
//    }
//
//    fun tableExists(tableName: String): Boolean? {
//        val cursor = db?.query("sqlite_master", arrayOf("name"), "type=? and name=?", arrayOf("table", tableName), null, null, null, null)
//        val exists = cursor?.moveToFirst()
//        cursor?.close()
//        return exists
//    }
//
//    fun deleteDb(name:String){
//        context.deleteDatabase(name)
//    }
//
//    fun execSQL(){
//        db?.execSQL("DROP TABLE IF EXISTS books")
//        db?.execSQL("DROP TABLE IF EXISTS BOOKSHOP")
//    }
*/

    fun insertToDb(login: String, password: String, name: String, surname: String){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_LOGIN, login)
            put(MyDbNameClass.COLUMN_NAME_PASSWORD, password)
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_SURNAME, surname)
        }
        db?.insert(MyDbNameClass.TABLE_NAME1, null, values)
    }

//    fun insertToDbBook(title: String, author:String, description: String, cost: Double){
//        val values = ContentValues().apply {
//            put(MyDbNameClass.COLUMN_TITLE, title)
//            put(MyDbNameClass.COLUMN_AUTHOR, author)
//            put(MyDbNameClass.COLUMN_DESCRIPTION, description)
//            put(MyDbNameClass.COLUMN_COST, cost)
//        }
//        db?.insert(MyDbNameClass.TABLE_NAME2, null, values)
//    }
    
    fun readDbData():MutableList<MutableList<String>>{
        val dataList = mutableListOf<MutableList<String>>()
        val logins = ArrayList<String>()
        val passwords = ArrayList<String>()
        val cursorclient = db!!.query(MyDbNameClass.TABLE_NAME1, null, null, null, null, null, null)
        with(cursorclient){
            while (cursorclient?.moveToNext()!!){
                val login = cursorclient.getString(kotlin.math.abs(cursorclient.getColumnIndex(MyDbNameClass.COLUMN_NAME_LOGIN)))
                val password = cursorclient.getString(kotlin.math.abs(cursorclient.getColumnIndex(MyDbNameClass.COLUMN_NAME_PASSWORD)))
                logins.add(login.toString())
                passwords.add(password.toString())
                dataList.add(logins)
                dataList.add(passwords)
            }
        }
        cursorclient?.close()
        return dataList
    }

    fun isAdmin(login:String): Boolean {
        return login == "tursunbekzat07@gmail.com"
    }

    fun closeDb(){
        myDbHelper.close()
    }


    /*
    fun readDbDataBook(): MutableList<MutableList<String>>{
        val ids = ArrayList<String>()
        val titles = ArrayList<String>()
        val authors = ArrayList<String>()
        val descs = ArrayList<String>()
        val costs = ArrayList<String>()
        val cursorbook = db?.query(MyDbNameClass.TABLE_NAME2, null, null, null, null, null, null)
        with(cursorbook){
            if (cursorbook != null) {
                while (cursorbook.moveToNext()){
                    val id = cursorbook.getString(kotlin.math.abs(cursorbook.getColumnIndex(BaseColumns._ID)))
                    val title = cursorbook.getString(kotlin.math.abs(cursorbook.getColumnIndex(MyDbNameClass.COLUMN_TITLE)))
                    val author = cursorbook.getString(kotlin.math.abs(cursorbook.getColumnIndex(MyDbNameClass.COLUMN_AUTHOR)))
                    val desc = cursorbook.getString(kotlin.math.abs(cursorbook.getColumnIndex(MyDbNameClass.COLUMN_DESCRIPTION)))
                    val cost = cursorbook.getString(kotlin.math.abs(cursorbook.getColumnIndex(MyDbNameClass.COLUMN_COST)))
                    ids.add(id.toString())
                    titles.add(title.toString())
                    authors.add(author.toString())
                    descs.add(desc.toString())
                    costs.add(cost.toString())
                    books.add(ids)
                    books.add(titles)
                    books.add(authors)
                    books.add(descs)
                    books.add(costs)
                }
            }
        }
        cursorbook?.close()
        return books
    }

        fun updateBook(oldtitle:String, oldAuthor:String, title: String, author: String, description: String, cost: Double):Int? {
        val query =
            "SELECT COUNT(*) FROM ${MyDbNameClass.TABLE_NAME2} WHERE ${MyDbNameClass.COLUMN_TITLE} = ?"
        val selectionArgs = arrayOf(oldtitle)
        var res:Int? = null
        val cursor = db?.rawQuery(query, selectionArgs)
        cursor?.moveToFirst()

        val count: Int? = cursor?.getInt(0)

        if (count != null) {

            val values = ContentValues().apply {
                put("title", title)
                put("author", author)
                put("description", description)
                put("cost", cost)
            }
            val selection = "${MyDbNameClass.COLUMN_TITLE} = ? AND ${MyDbNameClass.COLUMN_AUTHOR} = ?"
            val selectionArgs2 = arrayOf(oldtitle, oldAuthor)
            Log.d("MyLog", "updated book")
            res = db?.update(MyDbNameClass.TABLE_NAME2, values, selection, selectionArgs2)
        }
        Log.d("MyLog", "not updated book")

        cursor?.close()
        return res
    }
    fun updateBook(oldTitle: String, oldAuthor: String, newTitle: String, newAuthor: String, description: String, cost: Double): Int {
        db = myDbHelper.writableDatabase
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_TITLE, newTitle)
            put(MyDbNameClass.COLUMN_AUTHOR, newAuthor)
            put(MyDbNameClass.COLUMN_DESCRIPTION, description)
            put(MyDbNameClass.COLUMN_COST, cost)
        }
        val selection = "${MyDbNameClass.COLUMN_TITLE} = ? AND ${MyDbNameClass.COLUMN_AUTHOR} = ?"
        val selectionArgs = arrayOf(oldTitle, oldAuthor)
        val res = db?.update(MyDbNameClass.TABLE_NAME2, values, selection, selectionArgs) ?: 0
//        db?.close()
        return res
    }


    fun deleteBook(title:String, author:String):Boolean {

        val query = "SELECT COUNT(*) FROM ${MyDbNameClass.TABLE_NAME2} WHERE ${MyDbNameClass.COLUMN_TITLE} = ?"
        val selectionArgs = arrayOf(title)

        val cursor = db?.rawQuery(query, selectionArgs)
        cursor?.moveToFirst()

        val count: Int? = cursor?.getInt(0)

        if (count != null) {
            val whereClause = "${MyDbNameClass.COLUMN_TITLE} = ? AND ${MyDbNameClass.COLUMN_AUTHOR} = ?"
            val whereArgs = arrayOf(title, author)
            db?.delete(MyDbNameClass.TABLE_NAME2, whereClause, whereArgs)
        }
        else {
            // the element does not exist in the table
            cursor?.close()
            return false
        }
        cursor.close()
        return true
    }

    fun searchBook(title:String): Book? {
//        val query = "SELECT * FROM books WHERE title = ? AND author = ?"
        val query = "SELECT * FROM books WHERE title = ?"
        val cursor = db?.rawQuery(query, arrayOf(title))
        var book: Book? = null
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // Retrieve data from cursor
                    val column2Data = cursor.getString(kotlin.math.abs(cursor.getColumnIndex("title")))
                    val column3Data = cursor.getString(kotlin.math.abs(cursor.getColumnIndex("author")))
                    val column4Data = cursor.getString(kotlin.math.abs(cursor.getColumnIndex("description")))
                    val column5Data = cursor.getString(kotlin.math.abs(cursor.getColumnIndex("cost"))).toString().toDouble()
                    book = Book(1, column2Data.toString(), column3Data.toString(), column4Data.toString(), column5Data)
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        return book
    }
*/




}