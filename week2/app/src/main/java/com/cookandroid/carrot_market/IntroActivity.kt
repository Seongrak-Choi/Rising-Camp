package com.cookandroid.carrot_market

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sp = getSharedPreferences("user_data",0)
        var login_token=sp.getInt("login_token",0)
        var handler = Handler()

        handler.postDelayed({
            if(login_token==1){
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else {
                var intent = Intent(this, BeginActivity::class.java)
                startActivity(intent)
            }
        },1000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}