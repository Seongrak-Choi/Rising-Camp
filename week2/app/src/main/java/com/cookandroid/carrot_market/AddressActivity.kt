package com.cookandroid.carrot_market

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cookandroid.carrot_market.databinding.ActivityAddressBinding

class AddressActivity : AppCompatActivity(){
    private lateinit var binding : ActivityAddressBinding
    lateinit var townArray : Array<String>
     var locationManager : LocationManager ?= null
     var locationListener : LocationListener ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)


        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        binding.AddressBtnSearch.setOnClickListener {
            if(ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),0)
            } else{
                locationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        var lat = 0.0
                        var lng = 0.0
                        if(location !=null){
                            lat=location.latitude
                            lng=location.longitude
                            Log.d("GmapViewFragment", "Lat: ${lat}, lon: ${lng}")
                            Toast.makeText(this@AddressActivity,"GmapViewFragment Lat: ${lat}, lon: ${lng}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        townArray = resources.getStringArray(R.array.townArray) //res-values-townstring.xml 안에 미리 배열 값을 넣어 두었고 그 배열을 불러오는 코드

        var adapter= TownAdapter(this,townArray)
        binding.AddressListviewTown.adapter=adapter

        binding.AddressListviewTown.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as String
            var intent = Intent(this,VertificationActivity::class.java)
            intent.putExtra("address",selectItem)
            startActivity(intent)
        }

    }

    override fun onPause() {
        super.onPause()
        locationListener?.let { locationManager?.removeUpdates(it) }
    }
}