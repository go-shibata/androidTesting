package com.example.go.androidtestingtest

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.assertj.core.api.SoftAssertions
import org.junit.After
import org.junit.Before
import org.junit.Test

class InputCheckerTest2 {
    lateinit var target: InputChecker

    @Before
    fun setUp() {
        target = InputChecker()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        val actual = target.isValid("abc123")
        assertThat(actual).isTrue()
    }
}