package com.appstyx.authtest.utils

import android.util.Log
import com.appstyx.authtest.data.model.BaseAPIResponse
import retrofit2.Response


const val GENERIC_ERROR_MESSAGE = "An error had occurred. Please try again"
const val GENERIC_ERROR_CODE = -1

fun <T : Any> getAPIResult(response: Response<BaseAPIResponse<T>>): DataResult<T> {
    Log.d("getAPIResult", "getAPIResult body: $response")
    if (response.isSuccessful) {
        val body = response.body()
        Log.d("getAPIResult", "getAPIResult body: ${body?.toString()}")

        return if (body?.data != null) {
            DataResult.Success(body.data)
        } else {
            DataResult.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }
    else {
        val errorBody = response.errorBody()
        Log.d("getAPIResult", "getAPIResult error: $errorBody")

        if (errorBody != null) {
            return DataResult.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }
    // Fallback to regular status code and message
    return DataResult.Error(response.code(), response.message())
}