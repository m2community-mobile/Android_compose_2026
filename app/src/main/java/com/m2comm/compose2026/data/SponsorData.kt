package com.m2comm.compose2026.data

class SponsorData : ArrayList<SponsorData.SponsorDataItem>(){
    data class SponsorDataItem(
        val image: String,
        val linkurl: String,
        val sid: String
    )
}