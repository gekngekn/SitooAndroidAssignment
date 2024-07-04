package com.gekn.sitooandroidassignment.utils

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.IOException

// Exception handler for coroutines to handle abruptions in the coroutines execution
// and continues the coroutine execution
val exceptionHandler = CoroutineExceptionHandler { _, exception ->
    when (exception) {
        is IOException -> Log.e("CoroutineExceptionHandler", "Coroutine IOException: ${exception.message}")
        is NullPointerException -> Log.e(
            "CoroutineExceptionHandler",
            "Coroutine NullPointerException: ${exception.message}"
        )
        else -> Log.e("CoroutineExceptionHandler", "Coroutine Exception: ${exception.message}")
    }
}