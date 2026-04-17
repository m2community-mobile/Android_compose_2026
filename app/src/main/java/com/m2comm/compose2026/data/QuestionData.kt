package com.m2comm.compose2026.data

class QuestionData : ArrayList<QuestionData.QuestionDataItem>(){
    data class QuestionDataItem(
        val room: String,
        val sid: String,
        val sub: List<Sub>,
        val theme: String
    ) {
        data class Sub(
            val etc_speaker: String,
            val sid: String,
            val speaker: String,
            val title: String
        )
    }
}