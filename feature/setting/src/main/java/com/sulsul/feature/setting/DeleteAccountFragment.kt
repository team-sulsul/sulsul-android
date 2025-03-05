package com.sulsul.feature.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.common.base.Constants
import com.sulsul.core.designsystem.view.dialog.OneButtonDialog
import com.sulsul.feature.setting.databinding.FragmentDeleteAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAccountFragment : BaseFragment<FragmentDeleteAccountBinding>() {

    private val settingViewModel: SettingViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDeleteAccountBinding {
        return FragmentDeleteAccountBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarTitle()
        initListener()
    }

    private fun initListener() {
        binding.tbDeleteAccount.btnToolbarBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        binding.cbDeleteAccount.setOnClickListener {
            binding.btnDeleteAccount.isEnabled = binding.cbDeleteAccount.isChecked
        }

        binding.btnDeleteAccount.setOnClickListener {
            val deleteAccountDialog = OneButtonDialog(
                title = getString(R.string.dialog_delete_account_title),
                subtitle = getString(R.string.dialog_delete_account_subtitle),
                button = getString(R.string.dialog_delete_account_confirm),
                onButtonClicked = {
                    settingViewModel.postDeleteAccount() {
                        settingViewModel.deleteToken()
                        moveToLoginActivity()
                    }
                }
            )
            deleteAccountDialog.show(childFragmentManager, "DELETE_ACCOUNT_DIALOG")
        }
    }

    private fun setToolbarTitle() {
        binding.tbDeleteAccount.tvToolbarTitle.text = getString(R.string.setting_delete_account)
    }

    private fun moveToLoginActivity() {
        val intent = Intent()
        intent.setClassName(requireContext(), "com.sulsul.feature.login.LoginActivity")
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(Constants.MEMBER_STATE, Constants.SIGN_OUT)
        startActivity(intent)
        ActivityCompat.finishAffinity(requireActivity())
    }
}
