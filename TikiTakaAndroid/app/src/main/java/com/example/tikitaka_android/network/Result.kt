package com.example.tikitaka_android.network

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T, val code: Int) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
}