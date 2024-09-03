package com.example.assignmentrxjava.data

import com.example.assignmentrxjava.data.datasource.RemoteSource
import com.example.assignmentrxjava.data.entity.Comments
import com.example.assignmentrxjava.data.entity.Post
import com.example.assignmentrxjava.domain.Repository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
) : Repository {
    override fun getPosts(): Single<List<Post>> {
        return remoteSource.getPosts()
    }

    override fun getComments(): Single<Comments> {
        return remoteSource.getComments()
    }
}