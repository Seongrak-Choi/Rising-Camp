package com.cookandroid.carrot_market.info

class AddressInfo(
    private var address : String,
    private var bookmark : String){

    fun getAddress():String{
        return address
    }

    fun getBookmark():String{
        return bookmark
    }

    fun setBookmarkTrue(){
        bookmark="t"
    }

    fun setBookmarkFalse(){
        bookmark="f"
    }

}