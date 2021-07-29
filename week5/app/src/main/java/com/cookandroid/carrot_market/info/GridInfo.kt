package com.cookandroid.carrot_market.info

data class GridInfo(
    private var imgSrc : Int,
    private var name : String){


    fun getImgSrc() : Int{
        return imgSrc
    }

    fun getName() : String{
        return name
    }

}

