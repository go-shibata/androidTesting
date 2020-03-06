package com.example.go.androidtestingtest.async

class StringFetcher {
    fun fetch(): String {
        Thread.sleep(1000L)
        return "foo"
    }
}