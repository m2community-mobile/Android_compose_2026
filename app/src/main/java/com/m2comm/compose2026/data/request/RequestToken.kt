package com.m2comm.compose2026.data.request

data class RequestToken(
    val sid: String,
    val push_token: String,
    val device_id: String,
    val device: String
)