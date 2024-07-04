package com.gekn.sitooandroidassignment.network

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T): NetworkResult<T>()
    data class Error(val code: Int, val message: String): NetworkResult<Nothing>()
}