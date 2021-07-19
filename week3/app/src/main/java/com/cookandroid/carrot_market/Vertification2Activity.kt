package com.cookandroid.carrot_market

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.databinding.ActivityVertification2Binding

class Vertification2Activity : AppCompatActivity(){
    private lateinit var binding : ActivityVertification2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVertification2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var address = intent.getStringExtra("address")

        var sp = getSharedPreferences("user_data",0)
        var phoneNum = sp.getString("phoneNum","")
        binding.vertification2EdtPhoneNum.setText(phoneNum.toString())

        binding.vertification2BtnAgree.setOnClickListener {
            var editor =sp.edit()
            editor.putString("adrress",address)
            editor.putInt("login_token",1)
            editor.commit()

            var intent = Intent(this,MainActivity::class.java)
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