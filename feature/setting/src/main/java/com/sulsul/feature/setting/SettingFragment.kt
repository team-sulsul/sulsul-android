package com.sulsul.feature.setting

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.designsystem.view.dialog.TwoButtonDialog
import com.sulsul.feature.setting.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private val settingViewModel: SettingViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingViewModel.initialize()
        observeUserInfo()
        initListener()
        setAppVersion()
    }

    private fun setAppVersion() {
        val versionName = try {
            requireContext().packageManager.getPackageInfo(requireContext().packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }

        binding.tvSettingAppVersion.text = versionName ?: "0.0"
    }

    private fun observeUserInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            settingViewModel.userInfo.collectLatest {
                binding.tvSettingUserNickname.text = it.nickname

                val amountComment = getString(R.string.setting_total_drink_amount, it.totalBottle, it.totalDrink)
                val highlightColor = ContextCompat.getColor(requireContext(), com.sulsul.core.designsystem.R.color.blue_300)
                val startIndex = 10
                val spannable = SpannableStringBuilder(amountComment)
                spannable.setSpan(
                    ForegroundColorSpan(highlightColor),
                    startIndex,
                    amountComment.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                binding.tvSettingTotalAmount.text = spannable
            }
        }
    }

    private fun initListener() {
        binding.containerSettingNotification.setOnClickListener {
            val notificationUrl = ""
            val title = getString(R.string.setting_notification)
            setNavAction(notificationUrl, title)
        }

        binding.containerSettingTermsOfUse.setOnClickListener {
            val termsOfUseUrl = "https://thorn-vest-300.notion.site/f7cba50b004143d98c5b165108c8d540"
            val title = getString(R.string.setting_terms_of_use)
            setNavAction(termsOfUseUrl, title)
        }

        binding.containerSettingPrivacyPolicy.setOnClickListener {
            val privacyPolicyUrl = "https://thorn-vest-300.notion.site/9c2ffbb51e3249e4bf87e2914cdb1180?pvs=4"
            val title = getString(R.string.setting_privacy_policy)
            setNavAction(privacyPolicyUrl, title)
        }

        binding.btnSettingLogout.setOnClickListener {
            val logoutDialog = TwoButtonDialog(
                title = getString(R.string.setting_logout),
                subtitle = getString(R.string.dialog_logout_subtitle),
                leftButton = getString(R.string.dialog_logout_no),
                rightButton = getString(R.string.dialog_logout_execute),
                onLeftButtonClicked = {},
                onRightButtonClicked = {
                    settingViewModel.deleteToken()
                    moveToLoginActivity()
                }
            )
            logoutDialog.show(childFragmentManager, "LOGOUT_DIALOG")
        }

        binding.tvSettingDeleteAccount.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_settingFragment_to_deleteAccountFragment)
        }
    }

    private fun setNavAction(termUrl: String, title: String) {
        val action = SettingFragmentDirections.actionSettingFragmentToTermsWebViewFragment(termUrl, title)
        findNavController().navigate(action)
    }

    private fun moveToLoginActivity() {
        val intent = Intent()
        intent.setClassName(requireContext(), "com.sulsul.feature.login.LoginActivity")
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        ActivityCompat.finishAffinity(requireActivity())
    }
}
