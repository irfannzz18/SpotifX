package com.snuffy.app

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import org.json.JSONObject
import java.io.File
import java.util.concurrent.Executors

class MainActivity : Activity() {
    private lateinit var web: WebView
    private val worker = Executors.newSingleThreadExecutor()
    private var downloadedPlayer: MediaPlayer? = null

    @Suppress("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Python.isStarted()) Python.start(AndroidPlatform(this))

        web = WebView(this).apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            addJavascriptInterface(NativeBridge(), "SpotifX")
        }
        setContentView(web)
        web.loadUrl("file:///android_asset/index.html")
    }

    private fun sendDownloadResult(json: String) {
        runOnUiThread {
            if (::web.isInitialized) web.evaluateJavascript("window.onSpotifXDownload && window.onSpotifXDownload($json)", null)
        }
    }

    private inner class NativeBridge {
        @JavascriptInterface
        fun downloadAudio(url: String) {
            worker.execute {
                try {
                    val outputDir = File(filesDir, "music").apply { mkdirs() }
                    val module = Python.getInstance().getModule("downloader")
                    val result = module.callAttr("download_audio", url, outputDir.absolutePath).toString()
                    sendDownloadResult(result)
                } catch (error: Throwable) {
                    val result = JSONObject()
                        .put("ok", false)
                        .put("error", error.message ?: "Download failed")
                        .toString()
                    sendDownloadResult(result)
                }
            }
        }

        @JavascriptInterface
        fun playDownloaded(fileName: String) {
            runOnUiThread {
                try {
                    val safeName = File(fileName).name
                    val file = File(File(filesDir, "music"), safeName)
                    require(file.isFile && file.length() > 0) { "Downloaded audio file not found." }
                    downloadedPlayer?.release()
                    downloadedPlayer = MediaPlayer().apply {
                        setDataSource(file.absolutePath)
                        setOnPreparedListener { it.start() }
                        setOnCompletionListener {
                            it.release()
                            if (downloadedPlayer === it) downloadedPlayer = null
                        }
                        prepareAsync()
                    }
                } catch (error: Throwable) {
                    sendDownloadResult(JSONObject().put("ok", false).put("error", error.message ?: "Playback failed").toString())
                }
            }
        }

        @JavascriptInterface
        fun stopDownloaded() {
            runOnUiThread {
                downloadedPlayer?.runCatching { stop() }
                downloadedPlayer?.release()
                downloadedPlayer = null
            }
        }
    }

    @Deprecated("Deprecated in Android")
    override fun onBackPressed() {
        if (::web.isInitialized && web.canGoBack()) web.goBack() else super.onBackPressed()
    }

    override fun onDestroy() {
        downloadedPlayer?.release()
        downloadedPlayer = null
        worker.shutdownNow()
        if (::web.isInitialized) {
            web.removeJavascriptInterface("SpotifX")
            web.destroy()
        }
        super.onDestroy()
    }
}
