package com.cookandroid.carrot_market.`object`

import com.cookandroid.carrot_market.utils.ALADIN_API
import com.cookandroid.carrot_market.utils.OPEN_WHEATHER_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val openWeatherClient = iniOpenWeather()
    val aladinBookClient = initAladinBookApi()

    private fun iniOpenWeather() : Retrofit =
        Retrofit.Builder()
            .baseUrl(OPEN_WHEATHER_API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    private fun initAladinBookApi() : Retrofit =
        Retrofit.Builder()
            .baseUrl(ALADIN_API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}