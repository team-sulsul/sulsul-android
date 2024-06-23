package com.sulsul.feature.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.sulsul.core.common.base.BaseFragment
import com.sulsul.core.designsystem.view.dialog.OneButtonDialog
import com.sulsul.core.designsystem.view.dialog.TwoButtonDialog
import com.sulsul.feature.setting.databinding.FragmentDeleteAccountBinding

class DeleteAccountFragment : BaseFragment<FragmentDeleteAccountBinding>() {
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
            if (binding.cbDeleteAccount.isChecked) {
                binding.btnDeleteAccount.isEnabled = true
            } else {
                binding.btnDeleteAccount.isEnabled = false
            }
        }

        binding.btnDeleteAccount.setOnClickListener {
            val deleteAccountDialog = OneButtonDialog(
                title = getString(R.string.dialog_delete_account_title),
                subtitle = getString(R.string.dialog_delete_account_subtitle),
                button = getString(R.string.dialog_delete_account_confirm),
                onButtonClicked = {
                    // 로그인 activity 이동
                }
            )
            deleteAccountDialog.show(childFragmentManager, "DELETE_ACCOUNT_DIALOG")
        }
    }

    private fun setToolbarTitle() {
        binding.tbDeleteAccount.tvToolbarTitle.text = getString(R.string.setting_delete_account)
    }
}
