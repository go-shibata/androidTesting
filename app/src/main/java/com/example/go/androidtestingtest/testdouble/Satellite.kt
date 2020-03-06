package com.example.go.androidtestingtest.testdouble

class Satellite {
    fun getWeather(): Weather {
        return Weather.SUNNY
    }

    fun getWeather(latitude: Double, longitude: Double): Weather {
        return Weather.SUNNY
    }
}