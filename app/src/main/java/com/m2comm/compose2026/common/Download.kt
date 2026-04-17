package com.m2comm.compose2026.common

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat

class Download {

    var dlpath = "m2_compose2026"
    var newFilename: String? = null
    var dm: DownloadManager? = null
    var c: Context? = null
    var id: Long = 0

    fun download(fUrl: String?, c: Context?, filename: String) {
        try {
            this.c = c
            newFilename = "$dlpath/$filename"
            // Make a request
            val request = DownloadManager.Request(Uri.parse(fUrl))
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(newFilename)
                .setDescription("Download file")
            if (Environment.getExternalStorageState()
                == Environment.MEDIA_MOUNTED
            ) {
                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS, newFilename
                )
            }

            // enqueue
            dm = this.c!!.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            id = dm!!.enqueue(request)
            val intentFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            ContextCompat.registerReceiver(
                this.c!!,
                downloadReceiver,
                intentFilter,
                ContextCompat.RECEIVER_NOT_EXPORTED
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private val downloadReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val query = DownloadManager.Query()
            query.setFilterById(id)
            val cursor = dm!!.query(query)
            if (cursor.moveToFirst()) {
                val columIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                val status = cursor.getInt(columIndex)
                //다운로드되었는지 파악함.
                if (status == DownloadManager.STATUS_SUCCESSFUL) {
                    Toast.makeText(c, "Download Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}