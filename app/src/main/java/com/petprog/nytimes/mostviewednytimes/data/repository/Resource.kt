package com.petprog.nytimes.mostviewednytimes.data.repository

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failed<T>(val message: String) : Resource<T>()
    class Loading<T> : Resource<T>()
}