package com.example.assignmentrxjava.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.assignmentrxjava.data.entity.Post

// UserDao contains the methods used for accessing the database, including queries.
@Dao
interface PostDao {
    @Insert
    fun insertPostList(postList: List<Post>)

    @Query("SELECT * FROM postdata")
    fun getPostList(): List<Post>

    @Query("DELETE FROM postdata")
    fun clear()

    @Update
    fun updatePost(post: Post)
}