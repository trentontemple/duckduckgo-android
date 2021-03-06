/*
 * Copyright (c) 2018 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app.browser.downloader

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.AnyThread
import androidx.core.app.NotificationCompat
import com.duckduckgo.app.browser.R
import com.duckduckgo.app.notification.NotificationRegistrar.ChannelType
import javax.inject.Inject

@AnyThread
class FileDownloadNotificationManager @Inject constructor(
    private val notificationManager: NotificationManager,
    private val applicationContext: Context
) {

    fun showDownloadInProgressNotification() {
        mainThreadHandler().post {
            Toast.makeText(applicationContext, R.string.downloadInProgress, Toast.LENGTH_LONG).show()

            val notification = NotificationCompat.Builder(applicationContext, ChannelType.FILE_DOWNLOADING.id)
                .setContentTitle(applicationContext.getString(R.string.downloadInProgress))
                .setSmallIcon(R.drawable.ic_file_download_white_24dp)
                .build()

            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    fun showDownloadFinishedNotification(
        filename: String,
        uri: Uri,
        mimeType: String?
    ) {
        mainThreadHandler().post {
            Toast.makeText(applicationContext, R.string.downloadComplete, Toast.LENGTH_LONG).show()

            val i = Intent(Intent.ACTION_VIEW)
            i.setDataAndType(uri, mimeType)

            val notification = NotificationCompat.Builder(applicationContext, ChannelType.FILE_DOWNLOADED.id)
                .setContentTitle(filename)
                .setContentText(applicationContext.getString(R.string.downloadComplete))
                .setContentIntent(PendingIntent.getActivity(applicationContext, 0, i, PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_file_download_white_24dp)
                .build()

            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    fun showDownloadFailedNotification() {
        mainThreadHandler().post {

            val notification = NotificationCompat.Builder(applicationContext, ChannelType.FILE_DOWNLOADED.id)
                .setContentTitle(applicationContext.getString(R.string.downloadFailed))
                .setSmallIcon(R.drawable.ic_file_download_white_24dp)
                .build()

            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    private fun mainThreadHandler() = Handler(Looper.getMainLooper())

    companion object {
        private const val NOTIFICATION_ID = 1
    }
}
