package com.appstyx.authtest.utils

sealed class LoadingStatus {

    object Success : LoadingStatus()
    object Loading : LoadingStatus()
    data class Error(val errorCode: Int, val errorMessage: String) : LoadingStatus()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success"
            is Error -> "Error[$errorCode: $errorMessage]"
            is Loading -> "Loading"
        }
    }
}