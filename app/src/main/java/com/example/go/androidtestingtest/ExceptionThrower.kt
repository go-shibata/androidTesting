package com.example.go.androidtestingtest

import java.lang.RuntimeException

class ExceptionThrower {
    fun throwRuntimeException() {
        throw RuntimeException("Aborted!")
    }
}