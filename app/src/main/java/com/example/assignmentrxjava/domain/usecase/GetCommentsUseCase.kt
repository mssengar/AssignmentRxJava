package com.example.assignmentrxjava.domain.usecase

import com.example.assignmentrxjava.domain.Repository
import com.example.assignmentrxjava.data.entity.Comments
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class GetCommentsUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(
    ) : Single<Comments> {
        return repository.getComments()
    }
}