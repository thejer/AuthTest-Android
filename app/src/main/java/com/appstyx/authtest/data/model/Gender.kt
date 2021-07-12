package com.appstyx.authtest.data.model

import com.google.gson.annotations.SerializedName

data class Genders (val genders: MutableList<Gender>)

data class Gender(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)