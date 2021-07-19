package com.cookandroid.carrot_market

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.databinding.ActivitySystemSettingBinding

class SystemSettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySystemSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.systemSettingBtnLogout.setOnClickListener {

        }


    }
}