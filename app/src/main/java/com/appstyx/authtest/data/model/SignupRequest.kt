package com.appstyx.authtest.data.model

data class SignupRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String
)