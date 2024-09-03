package com.example.assignmentrxjava.presentation.state

import com.example.assignmentrxjava.data.entity.Post

sealed class StatePostList {

    object Loading : StatePostList()
    data class Success(val postList: List<Post>) : StatePostList()
    data class Failure(val message: String) : StatePostList()
}