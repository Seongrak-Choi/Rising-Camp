package com.cookandroid.carrot_market.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activitySearchTabLayout.addTab(binding.activitySearchTabLayout.newTab().setText("책 제목"))
        binding.activitySearchTabLayout.addTab(binding.activitySearchTabLayout.newTab().setText("저자"))
        binding.activitySearchTabLayout.addTab(binding.activitySearchTabLayout.newTab().setText("출판사"))

    }
}