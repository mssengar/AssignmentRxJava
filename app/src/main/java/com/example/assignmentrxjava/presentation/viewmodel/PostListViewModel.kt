package com.example.assignmentrxjava.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.assignmentrxjava.presentation.state.StatePostList
import com.example.assignmentrxjava.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsUseCase
): ViewModel() {

    val statePostsList = MutableLiveData<StatePostList>()

    init {
        getPostsList()
    }

    @SuppressLint("CheckResult")
    private fun getPostsList(): LiveData<StatePostList> {
        useCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { posts ->
                    statePostsList.postValue(StatePostList.Success(posts))
                },
                { error -> statePostsList.postValue(StatePostList.Failure(error.message?:"")) }
            )
        return statePostsList
    }
}