package com.appstyx.authtest.data.model

import com.google.gson.annotations.SerializedName

data class BaseAPIResponse<T>(
        @SerializedName("data")
        val data: T
)