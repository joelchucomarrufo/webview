package pe.mowui.webview

/**
 * Autor: Joel Martin Chuco Marrufo
 * Fecha: 14,August,2020
 * Github: https://github.com/joelchucomarrufo
 * LinkedIn: https://www.linkedin.com/in/joelchucomarrufo/
 */

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadUrl("https://tiendaluisito.com/?fbclid=IwAR15rhGvK9H27xSC_pvc8_CzSzQ2mSoT92R62O8i0H8Bq6I-_gw1TNCI8gs")
    }

    private fun loadUrl(url: String) {
        try {
            webview.settings.javaScriptEnabled = true
            webview?.settings?.domStorageEnabled = true
            webview?.isScrollbarFadingEnabled = true
            webview?.loadUrl(url)

            webview.webViewClient = object : WebViewClient() {

                override fun onPageFinished(view: WebView, url: String) {
                    hideLoading()
                }

                override fun shouldOverrideUrlLoading(wv: WebView, url: String): Boolean {
                    if (url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                        webview.goBack()
                        return true
                    }
                    return false
                }
            }

        } catch (ex: Exception) {
            hideLoading()
        }
    }

    private fun hideLoading() {
        pgbWeb?.visibility = View.GONE
    }
}