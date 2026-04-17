package com.m2comm.compose2026.common

object UrlData {

    const val APP_TAG = "sicem2026"

    const val EZV_URL = "https://ezv.kr:4447" //"https://ezv.kr:4447" 복구되면..
    const val WEB_URL = "http://ezv.kr"
    const val MAIN_URL = "${EZV_URL}/voting/php/"
    const val MAIN_API_URL = "${EZV_URL}/voting/php/"
    const val IMG_URL = "${EZV_URL}/voting/upload/photo/"

    const val CODE = "sicem2026"
    const val WEB_CODE = "sicem2026"

    const val PREF_DEVICE_ID = "device_id"
    const val USER_SID = "user_sid"
    const val USER_CODE = "user_code"
    const val USER_NAME = "user_name"
    const val USER_OFFICE = "user_office"
    const val USER_COUNTRY = "user_country"


    const val DAY_01 = "217"
    const val DAY_02 = "218"
    const val DAY_03 = "219"
    const val DAY_04 = "-1"

    const val DATE_01 = "2026-04-09"
    const val DATE_02 = "2026-04-10"
    const val DATE_03 = "2026-04-11"


    //http://ezv.kr/sicem2026/list.html

    //const val SICEM01 = "${EZV_URL}/${WEB_CODE}/html/greetings.html"
    const val SICEM01 = "${WEB_URL}/${WEB_CODE}/html/about/desk.html"

    const val SICEM02 = "${EZV_URL}/${WEB_CODE}/html/about/floor.html"
    const val SICEM03 = "${EZV_URL}/${WEB_CODE}/html/about/program.html"
    const val SICEM04 = "${EZV_URL}/${WEB_CODE}/html/about/event.html"
    const val SICEM05 = "${EZV_URL}/${WEB_CODE}/html/about/room.html"
    const val SICEM06 = "${EZV_URL}/${WEB_CODE}/html/about/oral.html"
    const val SICEM07 = "${EZV_URL}/${WEB_CODE}/html/about/exhibition.html"
    const val SICEM08 = "${EZV_URL}/${WEB_CODE}/html/about/pray.html"


    const val PROGRAM01 = "glance"
    const val OFFICIAL_PROGRAM = "${WEB_URL}/${WEB_CODE}/html/about/program.html"
    const val PROGRAM02 = "${MAIN_URL}session/list.php?tab=${DAY_01}&code=${CODE}"
    const val PROGRAM03 = "${MAIN_URL}session/list.php?tab=${DAY_02}&code=${CODE}"
    const val PROGRAM04 = "${MAIN_URL}session/list.php?tab=${DAY_03}&code=${CODE}"


    const val ABSTRACT = "abstract/category.php"

    const val SPEAKER01 = "faculty/list.php"

    const val SPECIAL01 = "${EZV_URL}/${WEB_CODE}/html/special/special.html"

    const val QUIZ = "${EZV_URL}/${WEB_CODE}/html/quiz/quiz.html"

    const val SURVEY = "${EZV_URL}/${WEB_CODE}/html/survey.html"

    //const val SPONSOR01 = "http://ezv.kr/sicem2026/html/exhibition.html"

    const val SPONSOR01 = "booth/list.php?code=${CODE}&tab=1"
    //const val SPONSOR02 = "${_root_ide_package_.com.m2comm.compose2026.common.UrlData.EZV_URL}/voting/php/booth/list.php?tab=1&code=${CODE}"
    const val SPONSOR02 = "${EZV_URL}/${WEB_CODE}/html/sponsor/exhibition.html"

    const val GENERAL01 = "${EZV_URL}/${WEB_CODE}/html/information/welcome.html"
    const val GENERAL02 = "${EZV_URL}/${WEB_CODE}/html/information/useful.html"

    const val DOWNLOAD = "${EZV_URL}/${WEB_CODE}/html/download.html"


    const val VENUE01 = "${EZV_URL}/${WEB_CODE}/html/venue/loc.html"
    const val VENUE02 = "${EZV_URL}/${WEB_CODE}/html/venue/transport01.html"
    const val VENUE03 = "${EZV_URL}/${WEB_CODE}/html/venue/incheon.html"

    const val NOTICE = "bbs/view.php"
    const val NOTICE_WEB_LIST = "bbs/list.php?code=${CODE}"
    const val SCHEDULE = "session/list.php?code=${CODE}&tab=-2"

    const val BOOTH_GUIDE = "http://ezv.kr/sicem2026/html/event/booth.html"
    const val POSTER_GUIDE = "http://ezv.kr/sicem2026/html/event/poster.html"


    //const val MYPAGE = "${_root_ide_package_.com.m2comm.compose2026.common.UrlData.EZV_URL}/${WEB_CODE}/php/mypage/information.php"
    const val MYPAGE = "https://www.ezv.kr:4447/sicem2026/php/mypage/registration.php"
    const val QR = "http://ezv.kr/sicem2026/php/qr.php"
    const val BOOTH = "${MAIN_URL}booth/event.php?code=${CODE}&include=Y"

    const val SEARCH = "session/list.php?code=${CODE}&tab=-3"
    const val FAVORITE = "session/list.php?code=${CODE}&tab=-2"
    const val MEMO = "session/list.php?code=${CODE}&tab=-6"

    const val NOW = "session/list.php?code=${CODE}&tab=-1"


    fun GLANCE_MAIN_URL(did:String) : String {
        return "${EZV_URL}/voting/php/session/glance.php?code=$CODE&deviceid=${did}"
    }
    fun GLANCE_URL(tab:String, did:String) : String {
        return "${EZV_URL}/voting/php/session/glance.php?code=$CODE&tab=$tab&deviceid=${did}"
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    const val SPONSOR_URL = "/voting/php/booth/get_list.php?code=${CODE}" //$EVENT_CODE test

    const val LOGIN = "/voting/php/login/${CODE}.php"

    const val PHOTO_LIST = "/voting/php/photo/get_photo.php"
    const val PHOTO_LIKE_URL = "/voting/php/photo/set_favor.php"
    const val TOKEN_URL = "/voting/php/token.php"

    const val GET_PUSH_URL = "/voting/php/bbs/get_push.php"

    const val SET_PUSH_URL = "/voting/php/bbs/set_push.php"


    const val NOTICE_LIST = "${EZV_URL}/voting/php/bbs/get_list.php?code=${CODE}"


    const val VERSION_CHECK = "${EZV_URL}/shin/android_version_check.php?code=$CODE"


    const val BOOTH_EVENT = "http://ezv.kr/sicem2026/html/booth.html"
    const val BOOTH_SCAN_URL = "booth/set_post.php"
    const val BOOTH_ATTEND_CNT_URL = "booth/get_event_cnt.php"
    const val POSTER_ATTEND_CNT_URL = "booth/get_oral_event_cnt_endo.php"

    const val POSTER_SCAN_URL = "booth/set_post_endo.php"

    const val QR_SCAN_URL = "http://ezv.kr/voting/admin/php/regist/check_in_compose2026_app.php"
    const val VOTING_URL = "/voting/php/voting/app/post.php"

    const val GET_QUESTION = "/voting/php/session/get_session.php"
    const val SEND_QUESTION = "/voting/php/question/post.php"


    const val EVENT_HOME = "https://www.ezv.kr:4447/sicem2026/php/information/event.php"

    const val EVENT_POSTER_BOOTH = "http://ezv.kr/sicem2026/php/booth.php"




}