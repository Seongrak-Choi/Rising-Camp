package com.cookandroid.carrot_market.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.databinding.ActivityVertification2Binding
import com.google.android.material.snackbar.Snackbar

class Vertification2Activity : AppCompatActivity(){
    private lateinit var binding : ActivityVertification2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVertification2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var phoneNum = intent.getStringExtra("phoneNum")

        var sp = getSharedPreferences("user_data",0)

        Snackbar.make(binding.root,"인증번호가 문자로 전송됐습니다. (최대 20초 소요)", Snackbar.LENGTH_SHORT).show()

        binding.vertification2EdtPhoneNum.setText(phoneNum.toString())

        binding.vertification2BtnAgree.setOnClickListener {
            var editor =sp.edit()
            editor.putInt("login_token",1)
            editor.commit()

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.vertification2EdtCertification.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.vertification2BtnAgree.isEnabled = binding.vertification2EdtCertification.text.length==4
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
}