package com.m2comm.compose2026.api

import com.m2comm.compose2026.common.UrlData
import com.m2comm.compose2026.data.BoothCntResponse
import com.m2comm.compose2026.data.LoginData
import com.m2comm.compose2026.data.NoticeData
import com.m2comm.compose2026.data.PhotoData
import com.m2comm.compose2026.data.QuestionData
import com.m2comm.compose2026.data.ResponseEvent
import com.m2comm.compose2026.data.ResponseLogin
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
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ApiRepository {

    override suspend fun login(email: String, did:String): LoginData {
        return apiService.login(
            UrlData.CODE,email, did
        ).body()!!
    }

    override suspend fun getSponsor(): SponsorData {
        return apiService.getSponsors().body()!!
    }

    override suspend fun likePhoto(did: String, value: String, sid: String): String {
        return apiService.likePhoto(deviceid = did, value = value, photoSid = sid).body()!!
    }

    override suspend fun getPhotoList(code: String, tab: String, deviceid: String): PhotoData? {
        return apiService.getPhotoList(code, tab, deviceid).body()!!
    }

    override suspend fun getNotice(): NoticeData {
        return apiService.getNoticeList().body()!!
    }

    override suspend fun postToken(param: RequestToken): String {
        return apiService.postToken(deviceid = param.device_id, device = param.device, token = param.push_token).body()!!
    }

    override suspend fun setPush(param: SetPushRequest): String {
        return  apiService.setPush(deviceid = param.deviceid, value = param.value).body()!!
    }

    override suspend fun getPush(deviceid: String): String {
        return apiService.getPush(deviceid = deviceid).body()!!
    }

    override suspend fun versionCheck(): String {
        return apiService.versionCheck().body()!!
    }

    override suspend fun boothScan(param: BoothScanRequest): String {
        return apiService.boothScan(deviceid = param.deviceid, userSid = param.userSid, boothId = param.boothId).body()!!
    }

    override suspend fun posterScan(param: BoothScanRequest): String {
        return apiService.posterScan(deviceid = param.deviceid, userSid = param.userSid, barcode = param.boothId).body()!!
    }

    override suspend fun posterAttendCount(param: BoothAttendRequest): BoothCntResponse {
        return apiService.posterAttendCount(deviceid = param.deviceid, userSid = param.userSid).body()!!
    }

    override suspend fun qrScan(param: QrScanRequest): ResponseSimple {
        return apiService.qrScan(deviceid = param.device_id, userSid = param.user_sid, barcode = param.barcode).body()!!
    }

    override suspend fun getGift(param: GiftRequest): String {
        return apiService.getGift(deviceid = param.deviceid, userSid = param.userSid, gift = param.gift).body()!!
    }

    override suspend fun boothAttendCount(param: BoothAttendRequest): BoothCntResponse {
        return apiService.boothAttendCount(deviceid = param.deviceid, userSid = param.userSid).body()!!
    }

    override suspend fun sendVoting(req: VotingRequest): String {
        return apiService.sendVoting(deviceid = req.deviceid, value = req.value, room = req.room).body()!!
    }

    override suspend fun setToken(req: RequestToken): String {
        return apiService.postToken(deviceid = req.device_id, device = req.device, token = req.push_token).body()!!
    }

    override suspend fun getQuestion(sid: String): QuestionData {
        return apiService.getQuestion(sid=sid).body()!!
    }

    override suspend fun sendQuestion(param: QuestionRequest): String {
        return apiService.sendQuestion(
            question = param.question,
            deviceid = param.deviceid,
            device = param.device,
            lecture = param.lecture,
            session = param.session,
            sub = param.sub,
            room = param.room,
            name = param.name,
            mobile = param.mobile,
        ).body()!!
    }


}