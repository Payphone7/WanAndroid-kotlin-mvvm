package com.wanandroid.xp_web.ui

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.xp_commom.base.BaseActivity
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_web.R
import com.wanandroid.xp_web.databinding.ActivityWebBinding


@Route(path = Constants.PATH_WEB)
class WebActivity : BaseActivity<ActivityWebBinding>() {

    private lateinit var webView: WebView

    @JvmField
    @Autowired(name = Constants.WEB_TITLE)
    var title: String = ""

    @JvmField
    @Autowired(name = Constants.WEB_LINK)
    var url: String? = ""
    override fun init() {
        mBinding.ivBack.setOnClickListener { finish() }
        mBinding.tvTitle.text = title

        if (url.isNullOrEmpty()){
            finish()
        }


        webView = WebView(applicationContext)
        mBinding.webContainer.addView(webView)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.domStorageEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.d(TAG, "onPageStarted: ")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d(TAG, "onPageFinished: ")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.d(TAG, "shouldOverrideUrlLoading: =====2")
                if (url?.equals(request?.url) == false) {
                    Log.d(TAG, "shouldOverrideUrlLoading: =====3")
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(request?.url.toString()))
                    )
                    return true
                }
                return false
            }
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                Log.d(TAG, "onProgressChanged: $newProgress")
                mBinding.progressBar.progress = newProgress
                if (newProgress == 100) {
                    mBinding.progressBar.visibility = View.GONE
                }
            }
        }

        webView.loadUrl(url!!)
    }

    override fun observer() {

    }

    override fun getLayoutId() = R.layout.activity_web
}