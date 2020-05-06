package com.uttampanchasara.fluttertoastexample

/**
 * Created by Uttam on 06/05/20.
 */
import io.flutter.plugin.common.PluginRegistry.Registrar

object WebViewPlugin {
    fun registerWith(registrar: Registrar, activity: MainActivity) {
        registrar
                .platformViewRegistry()
                .registerViewFactory(
                        "webview", WebViewFactory(registrar.messenger(), activity))
    }
}