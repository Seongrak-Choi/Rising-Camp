package com.cookandroid.carrot_market.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.carrot_market.R
import com.cookandroid.carrot_market.`interface`.AladinBookInterface
import com.cookandroid.carrot_market.`interface`.WeatherInterface
import com.cookandroid.carrot_market.`object`.RetrofitClient
import com.cookandroid.carrot_market.adapter.ProductAdapter
import com.cookandroid.carrot_market.databinding.FragmentProductListviewBinding
import com.cookandroid.carrot_market.info.AladinBookInfo
import com.cookandroid.carrot_market.info.BookItem
import com.cookandroid.carrot_market.info.ProductInfo
import com.cookandroid.carrot_market.utils.ALADIN_API
import com.cookandroid.carrot_market.utils.OPEN_WHEATHER_API
import com.example.retrofit_weathertest.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentProductListView : Fragment(){

    val productList = ArrayList<ProductInfo>(5)
    private var aladinBookList = mutableListOf<BookItem>()
    lateinit var binding : FragmentProductListviewBinding
    private lateinit var productAdapter: ProductAdapter
    val aladinBookInterface = RetrofitClient.aladinBookClient.create(AladinBookInterface::class.java)
    private var PAGE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentProductListviewBinding.inflate(layoutInflater)
        binding.mainFloatingBtn.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white)) //floatingButton의 아이콘 색상을 변경

        PAGE=0
        aladinBookList.clear()

        var sp = activity?.getSharedPreferences("user_data",0)
        var town = sp?.getString("town","")
        binding.mainTxAddress.text = town

        var handler = Handler(Looper.getMainLooper()) //api를 호출할 때 ui를 변경할 수 있도록 핸들러를 선언

        //현재 고양시의 온도를 받아오는 API호출
        Thread {
            handler.post{
                getWeatherData("Goyang",OPEN_WHEATHER_API.CLIENT_KEY)
            }
        }.start()

        //알라딘에서 베스트 셀러를 호출하는 함수를 실행
        Thread {
            if(PAGE<50){
                PAGE++
            }
            handler.post{
                getBookData("10",PAGE.toString(),"Book",ALADIN_API.VERSION,ALADIN_API.CLIENT_KEY,"Bestseller","js")
            }
        }.start()



        //스크롤이 더 이상 아래로 내렬갈 곳이 없을 때 동작하는 리스너
        binding.fragmentRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1


                if(!binding.fragmentRecyclerView.canScrollVertically(1)&&lastVisibleItemPosition==itemTotalCount){ // 아래로 스크롤이 더 이상 되지 않을 때 -1은 위로 안될때
                    if(PAGE<50) PAGE++
                    productAdapter.deleteLoading()

                    var addList = mutableListOf<BookItem>()

                    var handler = android.os.Handler()
                    handler.postDelayed({
                        aladinBookInterface.getBookBestseller(ALADIN_API.BESTSELLER_MAX_RESULTS, PAGE.toString(),
                        "Book",ALADIN_API.VERSION,ALADIN_API.CLIENT_KEY,"Bestseller","js").enqueue(
                        object : Callback<AladinBookInfo> {
                            override fun onResponse(call: Call<AladinBookInfo>, response: Response<AladinBookInfo>) {
                                if(response.isSuccessful){
                                    val result = response.body() as AladinBookInfo
                                    for(i in result.bookItem){
                                        addList.add(i)
                                    }
                                    addList.add(BookItem(" "," "," "," "," "," "," "," "," "))
                                    productAdapter.setList(addList)
                                    productAdapter.notifyItemRangeInserted((PAGE*20)-10,20)
                                }else{
                                    Log.d("MainActivity","getBookBestseller - onResponse : Error code ${response.errorBody()}")
                                }
                            }
                            override fun onFailure(call: Call<AladinBookInfo>, t: Throwable) {
                                Log.d("MainActivity",t.message ?: "통신오류")
                            }
                        })
                    },1000)
                }
            }
        })

        binding.fragmentProductListBtnSearch.setOnClickListener {
            var intent = Intent(context,SearchActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    //고양시의 날씨 API를 받아오는 함수
    private fun getWeatherData(city:String, key:String){
        val weatherInterface = RetrofitClient.openWeatherClient.create(WeatherInterface::class.java)
        weatherInterface.getWeather(city,key).enqueue(object :
            Callback<WeatherResponse> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if(response.isSuccessful){
                    val result = response.body() as WeatherResponse
                    val celsius:String= String.format("%.1f",result.getMain()?.temp?.toFloat()?.minus(273.15))
                    binding.fragmentProductListTxTemp.text= "$celsius \u00baC"

                    //날씨에 따라 아이콘을 변경해주는 코드
                    val weather = result.getWeather()[0].main.toString() //날씨의 정보를 String값으로 받아옴
                    when(weather){
                        "Snow"->{
                            binding.fragmentProductListImgWeather.setImageResource(R.drawable.snow)
                        }
                        "Clouds"->{
                            binding.fragmentProductListImgWeather.setImageResource(R.drawable.clouds)
                        }
                        "Rain"->{
                            binding.fragmentProductListImgWeather.setImageResource(R.drawable.rain)
                        }
                        "Clear"->{
                            binding.fragmentProductListImgWeather.setImageResource(R.drawable.clear)
                        }
                    }

                }else{
                    Log.d("MainActivity","getWeatherData - onResponse : Error code ${response.errorBody()}")
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("MainActivity",t.message ?: "통신오류")
            }
        })
    }

    //알라딘에서 책 정보 API받아오는 함수
    private fun getBookData(maxResult:String,start:String,searchTarget:String,version:String,ttbkey:String,queryType:String,output:String){
        aladinBookInterface.getBookBestseller(maxResult,start,searchTarget,version,ttbkey,queryType,output).enqueue(
            object : Callback<AladinBookInfo> {
                override fun onResponse(call: Call<AladinBookInfo>, response: Response<AladinBookInfo>) {
                    if(response.isSuccessful){
                        val result = response.body() as AladinBookInfo
                        for(i in result.bookItem){
                            aladinBookList.add(i)
                        }
                        aladinBookList.add(BookItem(" "," "," "," "," "," "," "," "," "))
                        binding.fragmentRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false) //리사이클러뷰에 레이아웃매니저를 부착
                        productAdapter= ProductAdapter(aladinBookList)
                        binding.fragmentRecyclerView.adapter =productAdapter //리사이클러뷰에 어댑터를 부착
                    }else{
                        Log.d("MainActivity","getBookBestseller - onResponse : Error code ${response.errorBody()}")
                    }
                }
                override fun onFailure(call: Call<AladinBookInfo>, t: Throwable) {
                    Log.d("MainActivity",t.message ?: "통신오류")
                }

            })
    }






}