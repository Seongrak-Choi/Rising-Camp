package com.example.retrofit_weathertest.model

class Weather {
    var icon: String? = null
    var description: String? = null
    var main: String? = null
    var id: String? = null


    override fun toString(): String {
        return "ClassPojo [icon = $icon, description = $description, main = $main, id = $id]"
    }
}