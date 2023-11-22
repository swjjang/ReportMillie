package com.swjjang7.reportmillie.screen.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.swjjang7.reportmillie.R
import com.swjjang7.reportmillie.databinding.ActivityWebDataBinding

class WebViewActivity : AppCompatActivity() {
    companion object {
        const val INTENT_URL = "url"

        fun newInstance(context: Context, url: String): Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtra(INTENT_URL, url)
            }
        }
    }

    private lateinit var binding: ActivityWebDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityWebDataBinding>(
            this,
            R.layout.activity_web_data
        ).also {
            it.lifecycleOwner = this
        }

        initWebView()

        intent.getStringExtra(INTENT_URL)?.let {
            binding.webView.loadUrl(it)
        }
    }

    private fun initWebView() {
        // 과제 특성상 특별한 처리가 없어 더이상의 처리는 하지 않음
        with(binding.webView) {
            setInitialScale(100)

            with(settings) {
                javaScriptEnabled = true
                textZoom = 100
                builtInZoomControls = true
                displayZoomControls = false
            }
        }
    }

}