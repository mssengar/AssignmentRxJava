package com.example.assignmentrxjava.domain

import com.example.assignmentrxjava.data.entity.Comments
import com.example.assignmentrxjava.data.entity.Post
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getPosts() : Single<List<Post>>

    fun getComments(): Single<Comments>
}