package com.example.assignmentrxjava.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postdata")
data class Post(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    var isFav: Boolean = false,
)
