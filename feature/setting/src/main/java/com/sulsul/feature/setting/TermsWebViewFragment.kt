package com.sulsul.feature.setting

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.navigation.fragment.navArgs
import android.webkit.WebViewClient
import androidx.navigation.Navigation
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.feature.setting.databinding.FragmentTermsWebviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsWebViewFragment : BaseFragment<FragmentTermsWebviewBinding>() {

    private val args: TermsWebViewFragmentArgs by navArgs()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentTermsWebviewBinding {
        return FragmentTermsWebviewBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWebView()
        setToolbar(args.title)
        loadUrl(args.url)
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

    private fun setToolbar(title: String) {
        binding.tbTermsWebview.tvToolbarTitle.text = title
        binding.tbTermsWebview.btnToolbarBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
    }
}
