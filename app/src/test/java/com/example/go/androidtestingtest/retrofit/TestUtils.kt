@file:JvmName("TestUtils")

package com.example.go.androidtestingtest.retrofit

import okhttp3.mockwebserver.MockResponse
import java.io.BufferedReader
import java.io.InputStreamReader

fun MockResponse.setBodyFromFileName(name: String): MockResponse {
    val inputStream = javaClass.classLoader?.getResourceAsStream(name)
        ?: throw RuntimeException("invalid")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder().apply {
        bufferedReader.forEachLine { buffer -> append(buffer) }
    }

    val body = stringBuilder.toString()
    this.setBody(body)
    return this
}