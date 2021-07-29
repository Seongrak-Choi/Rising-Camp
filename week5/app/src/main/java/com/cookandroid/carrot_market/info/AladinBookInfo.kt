package com.cookandroid.carrot_market.info

import com.google.gson.annotations.SerializedName


class AladinBookInfo (
    @SerializedName("item")
    var bookItem: MutableList<BookItem>,
    var searchCategoryName: String,
    var title: String,
    var version: String,
    var pubDate: String
)