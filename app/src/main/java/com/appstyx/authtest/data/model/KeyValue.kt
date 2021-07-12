package com.appstyx.authtest.data.model

data class KeyValue (
    val name: String,
    val id: String,
) {
    override fun toString(): String = name
}

data class KeyValues(
        val keyValues: List<KeyValue>
)