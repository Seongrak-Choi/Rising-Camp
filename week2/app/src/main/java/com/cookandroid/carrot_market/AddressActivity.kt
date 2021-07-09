package com.cookandroid.carrot_market

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.carrot_market.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity(){
    private lateinit var binding : ActivityAddressBinding
    lateinit var townArray : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        townArray = resources.getStringArray(R.array.townArray)

        var adapter= TownAdapter(this,townArray)
        binding.AddressListviewTown.adapter=adapter

        binding.AddressListviewTown.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as String
            var intent = Intent(this,VertificationActivity::class.java)
            intent.putExtra("address",selectItem)
            startActivity(intent)
        }
    }
}