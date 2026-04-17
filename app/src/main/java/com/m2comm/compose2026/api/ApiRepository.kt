package com.m2comm.compose2026.api

import com.m2comm.compose2026.common.UrlData.CODE
import com.m2comm.compose2026.data.BoothCntResponse
import com.m2comm.compose2026.data.LoginData
import com.m2comm.compose2026.data.NoticeData
import com.m2comm.compose2026.data.PhotoData
import com.m2comm.compose2026.data.QuestionData
import com.m2comm.compose2026.data.ResponseEvent
import com.m2comm.compose2026.data.ResponseQr
import com.m2comm.compose2026.data.ResponseSimple
import com.m2comm.compose2026.data.SponsorData
import com.m2comm.compose2026.data.request.BoothAttendRequest
import com.m2comm.compose2026.data.request.BoothScanRequest
import com.m2comm.compose2026.data.request.GiftRequest
import com.m2comm.compose2026.data.request.QrScanRequest
import com.m2comm.compose2026.data.request.QuestionRequest
import com.m2comm.compose2026.data.request.RequestToken
import com.m2comm.compose2026.data.request.SetPushRequest
import com.m2comm.compose2026.data.request.VotingRequest

interface ApiRepository {

    suspend fun login(email:String, did:String): LoginData

    suspend fun getSponsor():SponsorData

    suspend fun likePhoto(did:String, value:String, sid:String):String

    suspend fun getPhotoList(code:String=CODE, tab:String, deviceid:String):PhotoData?

    suspend fun getNotice():NoticeData

    suspend fun postToken(param: RequestToken):String

    suspend fun setPush(param: SetPushRequest) :String

    suspend fun getPush(deviceid: String) : String

    suspend fun versionCheck() : String

    suspend fun boothScan(param: BoothScanRequest) : String
    suspend fun posterScan(param: BoothScanRequest) : String

    suspend fun qrScan(param: QrScanRequest) : ResponseSimple

    suspend fun getGift(param: GiftRequest) : String

    suspend fun boothAttendCount(param: BoothAttendRequest) : BoothCntResponse
    suspend fun posterAttendCount(param: BoothAttendRequest) : BoothCntResponse

    suspend fun sendVoting(req: VotingRequest): String
    suspend fun setToken(req:RequestToken): String

    suspend fun getQuestion(sid: String) : QuestionData

    suspend fun sendQuestion(param: QuestionRequest) : String


    /*suspend fun login(id:String, pw:String): ResponseLogin

    */

}