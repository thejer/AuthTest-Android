package com.appstyx.authtest.data.model

data class KeyValue <T>(
    val name: String,
    val key: String,
    val id: String,
    val `object`: T
) {
    override fun toString(): String = name
}

data class KeyValues<T>(
        val keyValues: List<KeyValue<T>>
)