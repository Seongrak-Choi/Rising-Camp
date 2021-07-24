package com.cookandroid.carrot_market

data class RecommendInfo(
    private val imgSrc : Int,
    private val title : String,
    private val subTitle:String,
    private val postscriptCount:Int,
    private val patron : Int,
    private val contents : String,
    private val nickName : String){

    fun getImgSrc() : Int{
        return imgSrc
    }

    fun getTitle() : String{
        return title
    }

    fun getSubTitle() : String{
        return subTitle
    }

    fun getPostscriptCount() : Int{
        return postscriptCount
    }

    fun getPatron() : Int{
        return patron
    }

    fun getContents() : String{
        return contents
    }
    fun getNickName() : String{
        return nickName
    }
}
