package com.sulsul.feature.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.designsystem.view.dialog.TwoButtonDialog
import com.sulsul.feature.setting.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        binding.containerSettingNotification.setOnClickListener {
            val notificationUrl = "www.naver.com"
            val title = getString(R.string.setting_notification)
            setNavAction(notificationUrl, title)
        }

        binding.containerSettingTermsOfUse.setOnClickListener {
            val termsOfUseUrl = ""
            val title = getString(R.string.setting_terms_of_use)
            setNavAction(termsOfUseUrl, title)
        }

        binding.containerSettingPrivacyPolicy.setOnClickListener {
            val privacyPolicyUrl = ""
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
                    // 로그아웃 실행
                }
            )
            logoutDialog.show(childFragmentManager, "LOGOUT_DIALOG")
        }

        binding.tvSettingDeleteAccount.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_settingFragment_to_deleteAccountFragment)
        }
    }

    private fun setNavAction(termUrl: String, title: String){
        val action = SettingFragmentDirections.actionSettingFragmentToTermsWebViewFragment(termUrl, title)
        findNavController().navigate(action)
    }
}
