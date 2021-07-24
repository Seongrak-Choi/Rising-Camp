package com.cookandroid.carrot_market

import java.security.PrivilegedAction

data class MyNearNewsInfo(
    private var imgSrc : Int,
    private var title : String,
    private var contents : String,
    private var profileImg:Int,
    private var profileName:String,
    private var town : String){

    fun getImgSrc() : Int{
        return imgSrc
    }
    fun getTitle() : String{
        return title
    }
    fun getContents() : String{
        return contents
    }
    fun getProfileImg() : Int{
        return profileImg
    }
    fun getProfileName() : String{
        return profileName
    }
    fun getTown() : String{
        return town
    }
}
