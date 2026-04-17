package com.m2comm.compose2026.data

data class ResponseQr(
    val `data`: Data?,
    val message: String,
    val result: String
) {
    data class Data(
        val sid: String,
        val tab: String
    )
}