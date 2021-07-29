package com.cookandroid.carrot_market.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.carrot_market.databinding.FragmentMyCarrotBinding

class FragmentMyCarrot : Fragment() {
    private lateinit var binding : FragmentMyCarrotBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMyCarrotBinding.inflate(layoutInflater)

        binding.myCarrotBtnSetting.setOnClickListener {
            var intent = Intent(context, SystemSettingActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }


}