package com.cookandroid.carrot_market

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.cookandroid.carrot_market.databinding.ActivityAddressBinding
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class AddressActivity : AppCompatActivity(){
    private lateinit var binding : ActivityAddressBinding
    lateinit var townArray : ArrayList<AddressInfo>
    lateinit var townArray2 : ArrayList<AddressInfo>
    lateinit var sp : SharedPreferences
    var onActivityStateChanged:TownAdapter.OnActivityStateChagned?=null
    lateinit var adapter : TownAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp=getSharedPreferences("user_data",0)



        townArray = ArrayList()
        townArray2 = ArrayList()

        for(i in 0..23){
            var key="index"+i
            var str = sp.getString(key,"").toString()
            var str_split = str?.split(",")
            townArray.add(AddressInfo(str_split[0],str_split[1]))
        }


        townArray2.addAll(townArray)

        adapter= TownAdapter(this,townArray)
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