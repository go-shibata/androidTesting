package com.example.go.androidtestingtest.testdouble

class WeatherForecast(
    private val satellite: Satellite,
    private val recorder: WeatherRecorder,
    private val formatter: WeatherFormatter
) {

    fun shouldBringUmbrella(): Boolean {
        return when (satellite.getWeather()) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun shouldBringUmbrella(latitude: Double, longitude: Double): Boolean {
        return when (satellite.getWeather(latitude, longitude)) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather() {
        val weather = satellite.getWeather()
        val formatted = formatter.format(weather)
        recorder.record(formatted)
    }

    fun recordCurrentWeather(latitude: Double, longitude: Double) {
        val weather = satellite.getWeather(latitude, longitude)
        val formatted = formatter.format(weather)
        recorder.record(formatted)
    }

    fun recordCurrentWeatherWithRecord(latitude: Double, longitude: Double) {
        val weather = satellite.getWeather(latitude, longitude)
        val formatted = formatter.format(weather)
        recorder.record(Record(formatted))
    }
}