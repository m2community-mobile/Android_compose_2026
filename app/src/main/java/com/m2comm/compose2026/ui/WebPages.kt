package com.m2comm.compose2026.ui

import android.net.Uri
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

class WebPages {

}

object UrlData{
    const val APP_TAG = "sicem2026"

    const val DAY_01 = "217"
    const val DAY_02 = "218"
    const val DAY_03 = "219"

    const val CODE = "sicem2026"
    const val WEB_CODE = "sicem2026"

    const val EZV_URL = "https://ezv.kr:4447" //"https://ezv.kr:4447" 복구되면..
    const val WEB_URL = "http://ezv.kr"
    const val MAIN_URL = "${EZV_URL}/voting/php/"
    const val PROGRAM02 = "${MAIN_URL}session/list.php?tab=${DAY_01}&code=${CODE}"
}

@Composable
fun WebDetailScreen(navController: NavHostController, urls:String) {
    val context = LocalContext.current
    var webView: WebView? by remember { mutableStateOf(null) }

    // 뒤로가기 제어
    BackHandler {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            navController.popBackStack()
        }
    }
    Column(modifier = Modifier.fillMaxSize().background(Color.White).systemBarsPadding()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                WebView(ctx).apply {
                    settings.javaScriptEnabled = true
                    settings.textZoom = 100
                    settings.useWideViewPort = true
                    settings.javaScriptEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.defaultTextEncodingName = "utf-8"
                    settings.javaScriptCanOpenWindowsAutomatically = true
                    settings.setSupportMultipleWindows(false)
                    settings.domStorageEnabled = true
                    settings.builtInZoomControls = true
                    settings.displayZoomControls = false
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
                    loadUrl(urls)
                    webViewClient = object : WebViewClient() {

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            // lottieTimer() // 필요 시 호출
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            val url = request?.url?.toString() ?: return false
                            Log.d("Webview URL : ", url)

                            // 1. 뒤로가기 처리 (back.php)
                            if (url.contains("back.php")) {
                                if (view?.canGoBack() == true) {
                                    view.goBack()
                                } else {
                                    navController.popBackStack() // 또는 특정 화면으로 이동
                                }
                                return true
                            }

                            // 2. 세션 이동 (move_session.php)
                            if (url.contains("move_session.php")) {
                                val uri = Uri.parse(url)
                                val tab = uri.getQueryParameter("tab")
                                val sid = uri.getQueryParameter("sid")
                                val code = "YOUR_CODE" // UrlData.CODE 대용
                                val moveUrl = "https://ezv.kr:4447/voting/php/session/list.php?code=$code&tab=$tab#session$sid"
                                view?.loadUrl(moveUrl)
                                return true
                            }

                            // 3. 알람 설정 (add_alarm)
                            if (url.contains("add_alarm")) {
                                // 알람 로직 별도 함수 분리 추천
                                return true
                            }

                            // 4. PDF 처리
                            if (url.contains(".pdf")) {
                                try {
                                    /*val intent = Intent(Intent.ACTION_VIEW).apply {
                                        setDataAndType(Uri.parse(url), "application/pdf")
                                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    }*/
                                    //context.startActivity(intent)
                                } catch (e: Exception) {
                                    /*val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/viewer?url=$url"))
                                    context.startActivity(browserIntent)*/
                                }
                                return true
                            }


                            return false
                        }
                    }
                    webView = this
                }
            }
        )
    }

}
