package com.example.assignmentrxjava.data.datasource

import com.example.assignmentrxjava.data.ApiService
import com.example.assignmentrxjava.data.entity.Comments
import com.example.assignmentrxjava.data.entity.Post
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteSource {
    override fun getPosts(): Single<List<Post>> {
        return apiService.getPosts()
    }
    override fun getComments(): Single<Comments> {
        return apiService.getComments()
    }
}