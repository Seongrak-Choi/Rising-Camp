package com.cookandroid.carrot_market

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cookandroid.carrot_market.databinding.FragmentMyCarrotBinding
import com.cookandroid.carrot_market.databinding.FragmentTownLifeBinding

class FragmentTownLife : Fragment(){
    private lateinit var binding : FragmentTownLifeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTownLifeBinding.inflate(layoutInflater)


        return binding.root
    }

}