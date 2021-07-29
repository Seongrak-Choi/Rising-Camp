package com.cookandroid.carrot_market.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.adapter.TownAdapter
import com.cookandroid.carrot_market.databinding.ActivityAddressBinding
import com.cookandroid.carrot_market.info.AddressInfo
import java.util.*
import kotlin.collections.ArrayList

class AddressActivity : AppCompatActivity(){
    private lateinit var binding : ActivityAddressBinding
    lateinit var townArray : ArrayList<AddressInfo>
    lateinit var townArray2 : ArrayList<AddressInfo>
    lateinit var sp : SharedPreferences
    var onActivityStateChanged: TownAdapter.OnActivityStateChagned?=null
    lateinit var adapter : TownAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp=getSharedPreferences("user_data",0)

        var isNaverLogin = intent.getBooleanExtra("isNaverLogin",false)

        townArray = ArrayList()
        townArray2 = ArrayList()

        for(i in 0..23){
            var key="index"+i
            var str = sp.getString(key,"").toString()
            var str_split = str?.split(",")
            townArray.add(AddressInfo(str_split[0],str_split[1]))
        }


        townArray2.addAll(townArray)

        adapter= TownAdapter(this,townArray,isNaverLogin)
        binding.AddressListviewTown.adapter=adapter

        onActivityStateChanged = adapter.registerActivityState()

        binding.AddressEdtSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                var input_text  = binding.AddressEdtSearch.text.toString().toLowerCase(Locale.getDefault())
                search(input_text)
                adapter.sort()
            }
        })
    }

    override fun onPause() {
        onActivityStateChanged?.onPaused()
        super.onPause()
    }



    private fun search(charText : String){  //EditText에 검색한 값을 리스트배열과 비교해주는 코드
        townArray.clear()
        if(charText.length==0){
            townArray.addAll(townArray2)
        }else{
            for(i in townArray2){
                if(i.getAddress().toLowerCase().contains(charText)){
                    townArray.add(i)
                }
            }
        }
        adapter.notifyDataSetChanged()
    }

}