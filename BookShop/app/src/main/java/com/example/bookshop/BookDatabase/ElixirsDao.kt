package com.example.bookstore.BookDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookshop.Retrofit.BookModule
import kotlinx.coroutines.flow.Flow

@Dao
interface ElixirsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookModule)

    @Query("select * from books")
    fun getAllBook(): Flow<List<BookModule>>

    @Query("delete from books where id like :Did")
    suspend fun deleteBookById(Did: String)

    @Query("update books set name=:title, difficulty=:difficulty, characteristics=:characteristics where id=:Did")
    fun updateBook(Did: String, title: String, difficulty: String, characteristics: String)

    @Query("select * from books order by id")
    fun getAllBookInAscOrder(): Flow<List<BookModule>>

    @Query("select * from books order by id desc")
    fun getAllBookInDescOrder(): Flow<List<BookModule>>

}