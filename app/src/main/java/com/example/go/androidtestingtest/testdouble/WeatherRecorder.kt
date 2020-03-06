package com.example.go.androidtestingtest.testdouble

class WeatherRecorder {
    var weatherStr: String? = null
    var record: Record? = null

    fun record(weatherStr: String) {
        this.weatherStr = weatherStr
    }

    fun record(record: Record) {
        this.record = record
    }
}