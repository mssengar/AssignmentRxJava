package com.example.assignmentrxjava.data.datasource

import com.example.assignmentrxjava.data.entity.Comments
import com.example.assignmentrxjava.data.entity.Post
import io.reactivex.rxjava3.core.Single

interface RemoteSource {
    fun getPosts() : Single<List<Post>>

    fun getComments(): Single<Comments>
}