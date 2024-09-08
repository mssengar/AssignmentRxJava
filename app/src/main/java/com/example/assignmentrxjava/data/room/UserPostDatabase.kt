package com.example.assignmentrxjava.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignmentrxjava.data.entity.Post

@Database(entities = [Post::class], version = 1)
abstract class UserPostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}