package com.m2comm.compose2026.api


import com.m2comm.compose2026.common.UrlData.BOOTH_ATTEND_CNT_URL
import com.m2comm.compose2026.common.UrlData.BOOTH_SCAN_URL
import com.m2comm.compose2026.common.UrlData.CODE
import com.m2comm.compose2026.common.UrlData.GET_PUSH_URL
import com.m2comm.compose2026.common.UrlData.GET_QUESTION
import com.m2comm.compose2026.common.UrlData.LOGIN
import com.m2comm.compose2026.common.UrlData.NOTICE_LIST
import com.m2comm.compose2026.common.UrlData.PHOTO_LIKE_URL
import com.m2comm.compose2026.common.UrlData.SPONSOR_URL
import com.m2comm.compose2026.common.UrlData.PHOTO_LIST
import com.m2comm.compose2026.common.UrlData.POSTER_ATTEND_CNT_URL
import com.m2comm.compose2026.common.UrlData.POSTER_SCAN_URL
import com.m2comm.compose2026.common.UrlData.QR_SCAN_URL
import com.m2comm.compose2026.common.UrlData.SEND_QUESTION
import com.m2comm.compose2026.common.UrlData.SET_PUSH_URL
import com.m2comm.compose2026.common.UrlData.TOKEN_URL
import com.m2comm.compose2026.common.UrlData.VERSION_CHECK
import com.m2comm.compose2026.common.UrlData.VOTING_URL
import com.m2comm.compose2026.common.UrlData.WEB_CODE
import com.m2comm.compose2026.data.BoothCntResponse
import com.m2comm.compose2026.data.LoginData
import com.m2comm.compose2026.data.NoticeData
import com.m2comm.compose2026.data.PhotoData
import com.m2comm.compose2026.data.QuestionData
import com.m2comm.compose2026.data.ResponseEvent
import com.m2comm.compose2026.data.ResponseQr
import com.m2comm.compose2026.data.ResponseSimple
import com.m2comm.compose2026.data.SponsorData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun login(
        @Field("code") code: String,
        @Field("email") email: String,
        @Field("deviceid") did: String
    ): Response<LoginData>

    @POST(SPONSOR_URL)
    suspend fun getSponsors(
    ): Response<SponsorData>

    @POST(NOTICE_LIST)
    suspend fun getNoticeList(
    ): Response<NoticeData>

    @FormUrlEncoded
    @POST(PHOTO_LIST)
    suspend fun getPhotoList(
        @Field("code") code: String,
        @Field("tab") tab : String,
        @Field("deviceid") deviceid: String,
    ): Response<PhotoData?>

    @FormUrlEncoded
    @POST(PHOTO_LIKE_URL)
    suspend fun likePhoto(
        @Field("code") code: String = CODE,
        @Field("deviceid") deviceid: String,
        @Field("val") value: String,
        @Field("sid") photoSid: String,
    ): Response<String>

    @FormUrlEncoded
    @POST(TOKEN_URL)
    suspend fun postToken(
        @Field("code") code: String = CODE,
        @Field("deviceid") deviceid: String,
        @Field("device") device: String,
        @Field("token") token: String,
    ): Response<String>

    @FormUrlEncoded
    @POST(SET_PUSH_URL)
    suspend fun setPush(
        @Field("code") code: String = CODE,
        @Field("deviceid") deviceid: String,
        @Field("val") value: String
    ): Response<String>

    @FormUrlEncoded
    @POST(GET_PUSH_URL)
    suspend fun getPush(
        @Field("code") code: String = CODE,
        @Field("deviceid") deviceid: String
    ): Response<String>

    @POST(VERSION_CHECK)
    suspend fun versionCheck(
    ): Response<String>

    @POST(QR_SCAN_URL)
    suspend fun qrScan(
        @Query("barcode") barcode: String,
        @Query("deviceid") deviceid: String,
        @Query("user_sid") userSid: String
    ): Response<ResponseSimple>

    @POST(BOOTH_SCAN_URL)
    suspend fun boothScan(
        @Query("code") code: String = CODE,
        @Query("deviceid") deviceid: String,
        @Query("regist_sid") userSid: String,
        @Query("booth_sid") boothId: String,
    ): Response<String>

    @POST(POSTER_SCAN_URL)
    suspend fun posterScan(
        @Query("code") code: String = CODE,
        @Query("deviceid") deviceid: String,
        @Query("user_sid") userSid: String,
        @Query("barcode") barcode: String,
    ): Response<String>

    @POST(BOOTH_ATTEND_CNT_URL)
    suspend fun boothAttendCount(
        @Query("code") code: String = CODE,
        @Query("deviceid") deviceid: String,
        @Query("user_sid") userSid: String
    ): Response<BoothCntResponse>

    @POST(POSTER_ATTEND_CNT_URL)
    suspend fun posterAttendCount(
        @Query("code") code: String = CODE,
        @Query("deviceid") deviceid: String,
        @Query("user_sid") userSid: String
    ): Response<BoothCntResponse>

    @POST(BOOTH_ATTEND_CNT_URL)
    suspend fun getGift(
        @Query("code") code: String = CODE,
        @Query("deviceid") deviceid: String,
        @Query("user_sid") userSid: String,
        @Query("gift_info") gift: String
    ): Response<String>

    @FormUrlEncoded
    @POST(VOTING_URL)
    suspend fun sendVoting(
        @Field("code") code: String = CODE,
        @Field("val") value: String,
        @Field("deviceid") deviceid: String,
        @Field("room") room: String,
    ): Response<String>

    @FormUrlEncoded
    @POST(GET_QUESTION)
    suspend fun getQuestion(
        @Field("code") code: String = CODE,
        @Field("session_sid") sid: String
    ): Response<QuestionData>

    @FormUrlEncoded
    @POST(SEND_QUESTION)
    suspend fun sendQuestion(
        @Field("code") code: String = CODE,
        @Field("question") question: String,
        @Field("deviceid") deviceid: String,
        @Field("device") device: String,
        @Field("lecture") lecture: String,
        @Field("session") session: String,
        @Field("sub") sub: String,
        @Field("room") room: String,
        @Field("name") name: String,
        @Field("mobile") mobile: String,
    ): Response<String>

}