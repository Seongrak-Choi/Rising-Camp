package com.cookandroid.carrot_market

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.databinding.ActivityBeginBinding

class BeginActivity : AppCompatActivity(){
    private lateinit var binding : ActivityBeginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BeginBtnStart.setOnClickListener {
            var intent = Intent(this,AddressActivity::class.java)
            startActivity(intent)

        }
    }
}