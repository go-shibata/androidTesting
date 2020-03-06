package com.example.go.androidtestingtest

class InputChecker {
    fun isValid(text: String?): Boolean {
        text ?: throw IllegalArgumentException("Cannot be null")
        return text.length >= 3 && text.matches(Regex("[a-zA-Z0-9]+"))
    }
}