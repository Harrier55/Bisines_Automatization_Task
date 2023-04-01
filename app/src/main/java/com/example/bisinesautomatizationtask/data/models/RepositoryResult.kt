package com.example.bisinesautomatizationtask.data.models


sealed class RepositoryResult<out T>(val status: RepositoryStatus, val data: T?, val message: String?) {

    data class Success<out R>(val _data: R?) : RepositoryResult<R>(
        status = RepositoryStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String) : RepositoryResult<Nothing>(
        status = RepositoryStatus.ERROR,
        data = null,
        message = exception
    )
}

