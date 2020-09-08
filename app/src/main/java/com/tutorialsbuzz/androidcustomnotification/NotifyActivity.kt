package com.tutorialsbuzz.androidcustomnotification

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notify.*

class NotifyActivity : AppCompatActivity() {

    companion object {
        const val notify_title: String = "notify_title"
        const val notify_content: String = "notify_content"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify)

        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.GONE
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        };

        loadNotificationPage(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        loadNotificationPage(intent)
    }

    private fun loadNotificationPage(intent: Intent?): Unit {
        webview.loadUrl(intent?.dataString);
    }


}