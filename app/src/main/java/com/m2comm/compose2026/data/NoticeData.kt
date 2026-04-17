package com.m2comm.compose2026.data

class NoticeData : ArrayList<NoticeData.NoticeDataItem>(){
    data class NoticeDataItem(
        val content: String,
        val new: String,
        val notiYN: String,
        val sid: String,
        val signdate: String,
        val subject: String
    )
}