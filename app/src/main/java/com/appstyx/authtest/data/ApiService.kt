package com.appstyx.authtest.data

import com.appstyx.authtest.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("genders")
    suspend fun getGenders(): Response<BaseAPIResponse<Genders>>

    @GET("users/me")
    suspend fun getUserData(): Response<BaseAPIResponse<User>>

    @POST("users")
    suspend fun signup(signupRequest: SignupRequest): Response<TokenResponse>
}