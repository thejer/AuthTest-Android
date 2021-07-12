package com.appstyx.authtest.data

import android.util.Log
import com.appstyx.authtest.data.model.Genders
import com.appstyx.authtest.utils.DataResult
import com.appstyx.authtest.utils.GENERIC_ERROR_CODE
import com.appstyx.authtest.utils.GENERIC_ERROR_MESSAGE
import com.appstyx.authtest.utils.getAPIResult

class DataRepository(private val apiService: ApiService) {

    suspend fun getGenders():DataResult<Genders> {
        return try {
            val response = apiService.getGenders()
            getAPIResult(response)
        } catch (e: Exception) {
            Log.e("getGenders", ": ${e.localizedMessage}", e.cause)
            DataResult.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }
}