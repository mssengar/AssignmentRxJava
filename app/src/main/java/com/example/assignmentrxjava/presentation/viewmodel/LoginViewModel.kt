package com.example.assignmentrxjava.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    var isEmailValid = false
    var isPasswordValid = false

    fun enableSubmitButton() : Boolean {
        return (isEmailValid && isPasswordValid)
    }
}