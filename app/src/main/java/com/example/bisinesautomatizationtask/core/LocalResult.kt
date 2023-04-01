package com.example.bisinesautomatizationtask.core


sealed class LocalResult<out T>(val status: LocalStatus, val data: T?, val message: String?) {

    data class Success<out R>(val _data: R?) : LocalResult<R>(
        status = LocalStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String) : LocalResult<Nothing>(
        status = LocalStatus.ERROR,
        data = null,
        message = exception
    )
}

