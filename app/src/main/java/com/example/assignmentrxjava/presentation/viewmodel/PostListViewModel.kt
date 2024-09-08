package com.example.assignmentrxjava.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentrxjava.data.entity.Post
import com.example.assignmentrxjava.data.room.UserPostDatabase
import com.example.assignmentrxjava.di.NetworkChecker
import com.example.assignmentrxjava.presentation.state.StatePostList
import com.example.assignmentrxjava.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsUseCase,
    private val networkChecker: NetworkChecker,
    private val userPostDatabase: UserPostDatabase
) : ViewModel() {

    private val _statePostsList = MutableLiveData<StatePostList>()
    val statePostsList = _statePostsList

    init {
        getPostsList()
    }

    @SuppressLint("CheckResult")
    private fun getPostsList(): LiveData<StatePostList> {
        useCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { posts ->
                    if (networkChecker.isNetworkConnected()) {
                        userPostDatabase.postDao().clear()
                        userPostDatabase.postDao().insertPostList(posts)
                    }
                    _statePostsList.postValue(StatePostList.Success(posts))
                },
                { error ->
                    if (error.message == "check internet connection") {
                        val postListDB = userPostDatabase.postDao().getPostList()
                        _statePostsList.postValue(StatePostList.Success(postListDB))
                    } else {
                        _statePostsList.postValue(StatePostList.Failure(error.message ?: ""))
                    }
                }
            )
        return _statePostsList
    }

    fun updateFavInDb(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            userPostDatabase.postDao().updatePost(post)
        }
    }
}