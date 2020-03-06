package com.example.go.androidtestingtest.testdouble

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import kotlin.RuntimeException

@RunWith(MockitoJUnitRunner.Silent::class)
class WeatherForecastTest {

    @Mock(name = "MockSatellite")
    lateinit var satellite: Satellite
    @Mock(name = "MockRecorder")
    lateinit var recorder: WeatherRecorder
    @Spy
    lateinit var formatter: WeatherFormatter
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        whenever(satellite.getWeather()).thenReturn(Weather.SUNNY)
        whenever(satellite.getWeather(any(), any()))
            .thenAnswer { invocation ->
                val latitude = invocation.arguments[0] as Double
                val longitude = invocation.arguments[1] as Double

                if (latitude in 10.0..20.0 && longitude in 10.0..20.0) {
                    return@thenAnswer Weather.SUNNY
                } else {
                    return@thenAnswer Weather.RAINY
                }
            }
        whenever(satellite.getWeather(eq(1.0), eq(1.0))).thenReturn(Weather.CLOUDY)
        whenever(satellite.getWeather(eq(1.0), eq(-1.0))).thenReturn(Weather.RAINY)
        whenever(satellite.getWeather(eq(0.0), eq(0.0)))
            .thenThrow(RuntimeException("ERROR"))

        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenLatitude10_givenLongitude10_returnsFalse() {
        val actual = weatherForecast.shouldBringUmbrella(10.0, 10.0)
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenLatitude20_givenLongitude20_returnsFalse() {
        val actual = weatherForecast.shouldBringUmbrella(20.0, 20.0)
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenLatitudeLessThan10_givenLongitudeLessThan10_returnsTrue() {
        val actual = weatherForecast.shouldBringUmbrella(5.0, 5.0)
        assertThat(actual).isTrue()
    }

    @Test
    fun shouldBringUmbrella_givenLatitudeGreaterThan20_givenLongitudeGreaterThan20_returnsTrue() {
        val actual = weatherForecast.shouldBringUmbrella(30.0, 30.0)
        assertThat(actual).isTrue()
    }

    @Test
    fun shouldBringUmbrella_givenLatitude1_givenLongitude1_returnsFalse() {
        val actual = weatherForecast.shouldBringUmbrella(1.0, 1.0)
        assertThat(actual).isFalse()
    }

    @Test
    fun shouldBringUmbrella_givenLatitude1_givenLongitudeM1_returnsTrue() {
        val actual = weatherForecast.shouldBringUmbrella(1.0, -1.0)
        assertThat(actual).isTrue()
    }

    @Test
    fun shouldBringUmbrella_givenLatitude0_givenLongitude0_throwsRuntimeException() {
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { weatherForecast.shouldBringUmbrella(0.0, 0.0) }
            .withMessage("ERROR")
            .withNoCause()
    }

    @Test
    fun recordCurrentWeather_givenLatitude10_givenLongitude10_assertRecorderCalled_recordSUNNY() {
        weatherForecast.recordCurrentWeather(10.0, 10.0)
        verify(recorder, times(1)).record("Weather is SUNNY")
    }

    @Test
    fun shouldBringUmbrella_assertRecorderNotCalled() {
        weatherForecast.shouldBringUmbrella()
        verify(recorder, never()).record(any<String>())
    }

    @Test
    fun recordCurrentWeatherWithRecord_givenLatitude10_givenLongitude10_assertRecorderCalled_recordSUNNY() {
        weatherForecast.recordCurrentWeatherWithRecord(10.0, 10.0)
        argumentCaptor<Record>().apply {
            verify(recorder, times(1)).record(capture())
            assertThat(firstValue.weatherStr).isEqualTo("Weather is SUNNY")
        }
    }

    @Test
    fun recordCurrentWeather_assertFormatterCalled() {
        weatherForecast.recordCurrentWeather()
        verify(formatter, times(1)).format(any())
    }
}