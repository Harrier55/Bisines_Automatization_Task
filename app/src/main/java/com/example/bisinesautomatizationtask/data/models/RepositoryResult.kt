package com.example.bisinesautomatizationtask.data.models

private const val SUCCESS = "SUCCESS"
private const val ERROR = "ERROR"


sealed class RepositoryResult<out T>(val status: String, val data: T?, val message: String?) {

    data class Success<out R>(val _data: R?) : RepositoryResult<R>(
        status = SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String) : RepositoryResult<Nothing>(
        status = ERROR,
        data = null,
        message = exception
    )
}

