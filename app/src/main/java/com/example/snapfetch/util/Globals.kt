package com.example.snapfetch.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import timber.log.Timber

fun openExternalBrowser(context: Context, url: String?) {
    url?.let {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Timber.e(e, "Cannot open url: $url")
        }
    }
}
