package com.cookandroid.carrot_market.activity

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.utils.NAVER_MAP_API
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource


class TownVertificationActivity : FragmentActivity(), OnMapReadyCallback{

    private val TAG = "MainActivity"

    private val PERMISSION_REQUEST_CODE = 100
    private val PERMISSIONS = arrayOf<String>(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private lateinit var sp : SharedPreferences
    private lateinit var mLocationSource: FusedLocationSource
    private lateinit var mNaverMap: NaverMap
    private var latitude : Float? = null
    private var longitude : Float? = null
    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_vertification)

        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient(NAVER_MAP_API.CLINET_ID)

        //FloactionActionButtonView 이미지 변경
        findViewById<FloatingActionButton>(R.id.activity_town_vertification_floating_btn).setColorFilter(ContextCompat.getColor(applicationContext, R.color.carrot))

        //sharedPreferences 객체 생성
        sp = getSharedPreferences("user_data",0)

        //sharedPreferences에서 사용자 동네를 불러옴
        var town = sp.getString("town"," ")

        longitude = sp.getFloat("longitude",0f)//sharePreferences에서 경도를 불러와 저장
        latitude = sp.getFloat("latitude",0f) //sharePreferences에서 위도를 불러와 저장

        findViewById<TextView>(R.id.activity_town_vertification_tx1).text = Html.fromHtml("현재 위치가 내 동네로 설정한 <b>'$town'</b> 내에 있어요.")


        var btn_back = findViewById<ImageButton>(R.id.activity_town_vertification_btn_back)
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.activity_town_vertification_fragment_map) as MapFragment?
            ?:MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.activity_town_vertification_fragment_map,it).commit()
            }

        mapFragment.getMapAsync(this)

        mLocationSource = FusedLocationSource(this,PERMISSION_REQUEST_CODE)

        btn_back.setOnClickListener {
            this.finish()
        }

        //동네인증 완료하기 버튼을 누르면 실행
        findViewById<Button>(R.id.activity_town_vertification_btn_complete).setOnClickListener {
            var message = "$town 동네인증 성공!"

            var toast = Toast(this)
            var toastView = View.inflate(this,R.layout.snackbar_custom,null)
            var toastText = toastView.findViewById<TextView>(R.id.snackbar_custom_tx)
            toastText.text=message
            toast.view=toastView
            toast.setGravity(Gravity.BOTTOM or Gravity.CENTER,0,70.toPx())
            toast.show()
            this.finish()
        }


    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        var marker = Marker()
        marker.icon= OverlayImage.fromResource(R.drawable.img_map_marker)
        marker.width=100
        marker.height=108
        marker.position=LatLng(latitude!!.toDouble(),longitude!!.toDouble())

        //카메라 위치를 마커의 위치로 이동 시켜줌
        var cameraUpdate = CameraUpdate.scrollAndZoomTo(
            LatLng(latitude!!.toDouble(),longitude!!.toDouble()),16.0)
        //마지막 위치로 카메라를 이동 시킴
        naverMap.moveCamera(cameraUpdate)

        marker.map=naverMap

        mNaverMap = naverMap

        //버튼을 누르면 현재 위치 찍어줌
        findViewById<FloatingActionButton>(R.id.activity_town_vertification_floating_btn).setOnClickListener {
            //현재 위치 찾아서 찍어줌
        mNaverMap.locationSource=mLocationSource
        naverMap.addOnLocationChangeListener { location ->
            //현재 위치를 저장해서 다음 번 실행 때 마커의 위치로 사용함
            longitude=location.longitude.toFloat()
            latitude = location.latitude.toFloat()
        }

        ActivityCompat.requestPermissions(this,PERMISSIONS,PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                mNaverMap.locationTrackingMode=LocationTrackingMode.Follow
            }
        }

    }
    override fun onPause() {
        super.onPause()
        //마지막 위치를 sharedPreference에 저장해서 다음에 지도를 켤 때 마커가 표시되도록 구현한다.
        var editor = sp.edit()
        editor.putFloat("latitude",latitude!!)
        editor.putFloat("longitude",longitude!!)
        editor.commit()
    }
}