package com.sulsul.feature.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sulsul.core.common.base.BaseActivity
import com.sulsul.feature.main.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.sulsul.feature.calendar.R.id.calendarFragment -> {
                    binding.bnvMain.isVisible = true
                }
                com.sulsul.feature.report.R.id.reportFragment -> {
                    binding.bnvMain.isVisible = true
                }
                com.sulsul.feature.setting.R.id.settingFragment -> {
                    binding.bnvMain.isVisible = true
                }
                else -> binding.bnvMain.isVisible = false
            }

            binding.bnvMain.setupWithNavController(navController)
        }
    }
}
