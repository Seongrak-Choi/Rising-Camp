package com.example.retrofit_weathertest.model

class WeatherResponse {
    var visibility: String? = null
    var timezone: String? = null
    private var main: Main? = null
    private var clouds: Clouds? = null
    private var sys: Sys? = null
    var dt: String? = null
    private var coord: Coord? = null
    private lateinit var weather: Array<Weather>
    var name: String? = null
    var cod: String? = null
    var id: String? = null
    var base: String? = null
    private var wind: Wind? = null
    fun getMain(): Main? {
        return main
    }

    fun setMain(main: Main?) {
        this.main = main
    }

    fun getClouds(): Clouds? {
        return clouds
    }

    fun setClouds(clouds: Clouds?) {
        this.clouds = clouds
    }

    fun getSys(): Sys? {
        return sys
    }

    fun setSys(sys: Sys?) {
        this.sys = sys
    }

    fun getCoord(): Coord? {
        return coord
    }

    fun setCoord(coord: Coord?) {
        this.coord = coord
    }

    fun getWeather(): Array<Weather> {
        return weather
    }

    fun setWeather(weather: Array<Weather>) {
        this.weather = weather
    }

    fun getWind(): Wind? {
        return wind
    }

    fun setWind(wind: Wind?) {
        this.wind = wind
    }

    override fun toString(): String {
        return "ClassPojo [visibility = $visibility, timezone = $timezone, main = $main, clouds = $clouds, sys = $sys, dt = $dt, coord = $coord, weather = $weather, name = $name, cod = $cod, id = $id, base = $base, wind = $wind]"
    }
}