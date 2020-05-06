package com.uttampanchasara.fluttertoastexample

/**
 * Created by Uttam on 06/05/20.
 */
import android.annotation.SuppressLint
import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.webkit.WebView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.platform.PlatformView

class MyWebView internal constructor(context: MainActivity, messenger: BinaryMessenger, id: Int) : PlatformView, MethodCallHandler {
    private val webView: WebView
    private val methodChannel: MethodChannel

    override fun getView(): View {
        return webView
    }

    init {
        webView = WebView(context)
        methodChannel = MethodChannel(messenger, "webview$id")
        methodChannel.setMethodCallHandler(this)
    }

    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        when (methodCall.method) {
            "loadUrl" -> loadUrl(methodCall, result)
            else -> result.notImplemented()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadUrl(methodCall: MethodCall, result: Result) {
        val url = methodCall.arguments as String
        webView.loadUrl(url)
        webView.clearCache(true)
        webView.clearHistory()
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.domStorageEnabled = true
        result.success(null)
    }

    override fun dispose() {
    }
}