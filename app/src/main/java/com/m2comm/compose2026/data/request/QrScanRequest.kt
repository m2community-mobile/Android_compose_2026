package com.m2comm.compose2026.data.request

data class QrScanRequest(
    val device_id: String,
    val user_sid: String,
    val barcode: String,
)
