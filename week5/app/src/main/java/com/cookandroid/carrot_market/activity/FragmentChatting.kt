package com.cookandroid.carrot_market.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.carrot_market.databinding.FragmentChattingBinding
import com.cookandroid.carrot_market.databinding.FragmentMyCarrotBinding

class FragmentChatting : Fragment(){
    private lateinit var binding : FragmentChattingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChattingBinding.inflate(layoutInflater)


        return binding.root
    }

}