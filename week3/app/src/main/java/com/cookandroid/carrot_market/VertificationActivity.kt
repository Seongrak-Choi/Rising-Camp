package com.cookandroid.carrot_market

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.cookandroid.carrot_market.databinding.ActivityVertificationBinding
import com.google.android.material.snackbar.Snackbar

class VertificationActivity : AppCompatActivity(){
    private lateinit var binding : ActivityVertificationBinding
    lateinit var toastView : View
    lateinit var toastText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVertificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sp = getSharedPreferences("user_data",0)
        var phoneNum = sp.getString("phoneNum","")

        binding.vertificationEdtPhoneNum.setText(phoneNum)
        if(phoneNum.toString().length==11)
            binding.vertificationBtnSend.isEnabled=true

        binding.vertificationBtnSend.setOnClickListener {
            var intent = Intent(this,Vertification2Activity::class.java)
            intent.putExtra("phoneNum",binding.vertificationEdtPhoneNum.text.toString())
            startActivity(intent)
        }

        binding.vertificationEdtPhoneNum.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.vertificationBtnSend.isEnabled = binding.vertificationEdtPhoneNum.length()==11
            }
        })
    }

    override fun onStop() {
        super.onStop()
        var sp = getSharedPreferences("user_data",0)
        var editor =sp.edit()
        var phoneNum = binding.vertificationEdtPhoneNum.text.toString()
        editor.putString("phoneNum",phoneNum)
        editor.commit()
    }

}