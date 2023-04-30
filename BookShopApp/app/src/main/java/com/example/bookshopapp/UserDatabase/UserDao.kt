package com.example.bookshopapp.UserDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookshopapp.UserDatabase.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)
    @Query("select * from Users")
    fun getAllUser(): Flow<List<User>>
}