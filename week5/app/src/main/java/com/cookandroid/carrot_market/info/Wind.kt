package com.example.retrofit_weathertest.model

class Wind {
    var deg: String? = null
    var speed: String? = null

    override fun toString(): String {
        return "ClassPojo [deg = $deg, speed = $speed]"
    }
}