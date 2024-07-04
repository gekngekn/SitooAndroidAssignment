package com.gekn.sitooandroidassignment.ui

sealed class AppState {
    data object Default: AppState()
    data object Content: AppState()
    data object Loading: AppState()
    data class Error(val code: Int, val msg: String): AppState()
}