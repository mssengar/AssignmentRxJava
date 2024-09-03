package com.example.assignmentrxjava.di

import android.content.Context
import java.io.IOException

class ServerDownException(context: Context) : IOException() {
    override val message: String
        get() = "Server Down 500"
}