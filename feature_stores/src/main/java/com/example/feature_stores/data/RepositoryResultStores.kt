package com.example.feature_stores.data


private const val SUCCESS = "SUCCESS"
private const val ERROR = "ERROR"

sealed class RepositoryResultStores<out T>(val status: String, val data: T?, val message: String?) {


    data class Success<out R>(val _data: R?) : RepositoryResultStores<R>(
        status = SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String) : RepositoryResultStores<Nothing>(
        status = ERROR,
        data = null,
        message = exception
    )
}