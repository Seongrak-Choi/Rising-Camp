package com.cookandroid.carrot_market.`interface`

import com.cookandroid.carrot_market.info.AladinBookInfo
import com.example.retrofit_weathertest.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AladinBookInterface {
    @GET("ItemSearch.aspx")
    fun getBookSearch(@Query("ttbkey") ttbkey:String,@Query("Query") Query:String, @Query("QueryType") QueryType:String,
                      @Query("MaxResults") MaxResults:String, @Query("start") start:String,@Query("SearchTarget") SearchTarget:String,
                      @Query("output") output:String, @Query("Version") Version:String) : Call<AladinBookInfo>

    @GET("ItemList.aspx")
    fun getBookBestseller(@Query("MaxResults") MaxResults:String, @Query("start") start:String,
                          @Query("SearchTarget") SearchTarget:String,@Query("Version") Version:String,
                          @Query("ttbkey") ttbkey:String,@Query("QueryType") QueryType:String, @Query("output") output:String) : Call<AladinBookInfo>
}