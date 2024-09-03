package com.example.assignmentrxjava.domain.usecase

import com.example.assignmentrxjava.domain.Repository
import com.example.assignmentrxjava.data.entity.Post
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(
    ) : Single<List<Post>> {
        return repository.getPosts()
    }
}