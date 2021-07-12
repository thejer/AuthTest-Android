package com.appstyx.authtest.data

import com.appstyx.authtest.data.model.Genders
import com.appstyx.authtest.data.model.SignupRequest
import com.appstyx.authtest.data.model.TokenResponse
import com.appstyx.authtest.data.model.UserData
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
            DataResult.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }

    suspend fun signup(signupRequest: SignupRequest): DataResult<TokenResponse> {
        return try {
            val response = apiService.signup(signupRequest)
            getAPIResult(response)
        } catch (e: Exception) {
            DataResult.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }

    suspend fun getUser(token: String): DataResult<UserData> {
        return try {
            val response = apiService.getUserData("Bearer $token")
            getAPIResult(response)
        } catch (e: Exception) {
            DataResult.Error(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE)
        }
    }
}