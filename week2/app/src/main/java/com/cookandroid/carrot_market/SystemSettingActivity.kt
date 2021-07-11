package com.cookandroid.carrot_market

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.ColorSpace
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.databinding.ActivitySystemSettingBinding

class SystemSettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySystemSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sp = getSharedPreferences("user_data",0)

        binding.systemSettingBtnLogout.setOnClickListener {
            var builder = AlertDialog.Builder(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth)
            builder.setTitle("로그아웃")
            builder.setMessage("로그아웃 하시겠습니까?")
            builder.setPositiveButton("확인",DialogInterface.OnClickListener { dialog, which ->
                var intent = Intent(this,BeginActivity::class.java)
                var editor =sp.edit()
                editor.putInt("login_token",0)
                editor.commit()
                startActivity(intent)
            })
            builder.setNegativeButton("취소",null)
            builder.show()
        }
    }
}