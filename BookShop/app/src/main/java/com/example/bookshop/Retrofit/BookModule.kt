package com.example.bookshop.Retrofit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="books")
data class BookModule (
    @ColumnInfo(name="kind")
    val kind:String?,
    @PrimaryKey()
    val full_sort_key: String,
    @ColumnInfo(name="url")
    val url:String?,
    @ColumnInfo(name = "cover_color")
    val cover_color: String?,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "cover")
    val cover: String?,
    @ColumnInfo(name = "epoch")
    val epoch: String?,
    @ColumnInfo(name = "href")
    val href: String?,
    @ColumnInfo(name = "has_audio")
    val has_audio: Boolean?,
    @ColumnInfo(name = "genre")
    val genre: String?,
    @ColumnInfo(name = "simple_thumb")
    val simple_thumb: String?,
    @ColumnInfo(name = "slug")
    val slug: String?,
    @ColumnInfo(name = "cover_thumb")
    val cover_thumb: String?,
    @ColumnInfo(name = "liked")
    val liked: String? = null
    ) : Serializable