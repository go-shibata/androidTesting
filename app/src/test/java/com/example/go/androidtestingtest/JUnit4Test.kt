package com.example.go.androidtestingtest

import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.junit.Ignore
import org.junit.Test

class JUnit4Test {
    @Test
    fun addition_isCorrect() {
        assertThat(1 + 1, `is`(2))
    }

    @Test
    fun greater_isCorrect() {
        assertThat(100, greaterThan(50))
    }

    @Test
    fun hasItem_isCorrect() {
        assertThat(listOf("foo", "bar", "baz"), hasItem("bar"))
    }

    @Ignore("テスト対象が仮実装なので一時的にスキップ")
    @Test
    fun temporarilySkipThisTest() {
    }
}
