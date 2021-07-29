package com.cookandroid.carrot_market.`interface`

import com.example.retrofit_weathertest.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {
    @GET("weather")
    fun getWeather(@Query("q") q:String, @Query("appid") appid:String) : Call<WeatherResponse>
}