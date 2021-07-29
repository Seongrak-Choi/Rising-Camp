package com.cookandroid.carrot_market.info

class ProductInfo(
    private var imgSrc: Int,
    private var name: String,
    private var address: String,
    private var price: String,
    private var likeCount: Int,
    private var chatCount: Int) {


    fun getImgSrc(): Int {
        return imgSrc
    }

    fun getName(): String {
        return name
    }

    fun getAddress(): String {
        return address
    }

    fun getPrice(): String {
        return price
    }

    fun getLikeCount(): Int {
        return likeCount
    }

    fun getChatCount(): Int {
        return chatCount
    }


    fun setImgSrc(imgSrc: Int) {
        this.imgSrc = imgSrc
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun setPrice(price: String) {
        this.price = price
    }

    fun setLikeCount(likeCount: Int) {
        this.likeCount = likeCount
    }

    fun setChatCount(chatCount: Int) {
        this.chatCount = chatCount
    }

}