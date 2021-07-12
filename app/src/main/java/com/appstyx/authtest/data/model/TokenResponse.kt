package com.appstyx.authtest.data.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("token")
        val token: String,
        @SerializedName("refreshToken")
        val refreshToken: String
    )
}