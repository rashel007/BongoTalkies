package com.rashel.myapplication.common

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failed<T>(val data: T? = null, val message: String) : Resource<T>()
    object Loading : Resource<Nothing>()
}
