package com.m2comm.compose2026.data

class PhotoData : ArrayList<PhotoData.PhotoDataItem>(){
    data class PhotoDataItem(
        var cnt: String,
        val date: String,
        val deviceid: Any?,
        val etc1: Any?,
        var myfav: String,
        val sid: String,
        val title: String,
        val url: String
    )
}