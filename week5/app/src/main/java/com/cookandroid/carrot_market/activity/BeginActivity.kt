package com.cookandroid.carrot_market.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.databinding.ActivityBeginBinding
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

class BeginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeginBinding
    lateinit var mOAuthLoginInstance : OAuthLogin
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this

        binding.beginLinearNaver.setOnClickListener {
            mOAuthLoginInstance = OAuthLogin.getInstance()
            mOAuthLoginInstance.init(mContext,getString(R.string.naver_client_id),getString(R.string.naver_client_secret), getString(
                R.string.naver_client_name
            ))

            mOAuthLoginInstance.startOauthLoginActivity(this,mOAuthLoginHandler)
        }

        binding.BeginBtnStart.setOnClickListener {
            var intent = Intent(this, AddressActivity::class.java)
            startActivity(intent)
        }
    }
    val mOAuthLoginHandler : OAuthLoginHandler = object : OAuthLoginHandler(){
        override fun run(success: Boolean) {
            if(success){
                var intent = Intent(applicationContext, AddressActivity::class.java)
                intent.putExtra("isNaverLogin",true)
                startActivity(intent)
            }
            else{
                val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)
                Log.e("error","errorCode:" + errorCode)
                Log.e("error","errorDesc:" + errorDesc)
                Toast.makeText(
                    baseContext, "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAffinity(this)
        System.exit(0)
    }
}