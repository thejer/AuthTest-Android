package com.appstyx.authtest.data

import com.appstyx.authtest.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("genders")
    suspend fun getGenders(): Response<BaseAPIResponse<Genders>>

    @GET("users/me")
    suspend fun getUserData(@Header("Authorization") token: String): Response<BaseAPIResponse<UserData>>

    @POST("users")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<BaseAPIResponse<TokenResponse>>
}