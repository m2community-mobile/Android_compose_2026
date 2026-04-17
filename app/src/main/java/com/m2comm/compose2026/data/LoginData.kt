package com.m2comm.compose2026.data

data class LoginData(
    val `data`: Data,
    val message: String,
    val result: String
) {
    data class Data(
        val country: String,
        val name: String,
        val office: String,
        val user_code: String,
        val user_sid: String
    )
}