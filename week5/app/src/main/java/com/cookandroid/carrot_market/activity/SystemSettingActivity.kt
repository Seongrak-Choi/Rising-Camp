package com.cookandroid.carrot_market.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.databinding.ActivitySystemSettingBinding
import com.cookandroid.carrot_market.databinding.DialogSysSettingBinding

class SystemSettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySystemSettingBinding
    private lateinit var dialog_binding : DialogSysSettingBinding
    private lateinit var dialog_logout : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemSettingBinding.inflate(layoutInflater)
        dialog_binding = DialogSysSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sp = getSharedPreferences("user_data",0)

        dialog_logout = Dialog(this)
        dialog_logout.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_logout.setContentView(R.layout.dialog_sys_setting)

        binding.systemSettingBtnLogout.setOnClickListener {
            dialog_logout.show()

            var btn_positive = dialog_logout.findViewById<Button>(R.id.dialog_sys_setting_btn_positive)
            var btn_negative = dialog_logout.findViewById<Button>(R.id.dialog_sys_setting_btn_negative)

            btn_positive.setOnClickListener {
                var intent = Intent(this, BeginActivity::class.java)
                var editor =sp.edit()
                editor.putInt("login_token",0)
                editor.commit()
                startActivity(intent)
            }

            btn_negative.setOnClickListener {
                dialog_logout.dismiss()
            }
        }
    }
}