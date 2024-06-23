package com.sulsul.feature.setting

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.setting.databinding.FragmentTermsWebviewBinding

class TermsWebViewFragment : BaseFragment<FragmentTermsWebviewBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentTermsWebviewBinding {
        return FragmentTermsWebviewBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWebView()
    }

    private fun initWebView() {
        binding.webviewTerms.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    super.onReceivedError(view, request, error)
                }
            }
        }
    }

    private fun loadUrl(url: String) {
        binding.webviewTerms.loadUrl(url)
    }
}
