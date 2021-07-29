package com.example.retrofit_weathertest.model

class Main {
    var temp: String? = null
    var temp_min: String? = null
    var humidity: String? = null
    var pressure: String? = null
    var feels_like: String? = null
    var temp_max: String? = null

    override fun toString(): String {
        return "ClassPojo [temp = $temp, temp_min = $temp_min, humidity = $humidity, pressure = $pressure, feels_like = $feels_like, temp_max = $temp_max]"
    }
}