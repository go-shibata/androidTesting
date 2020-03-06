package com.example.go.androidtestingtest.async

import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class AsyncStringFetcherTest {

    lateinit var fetcher: StringFetcher
    lateinit var asyncStringFetcher: AsyncStringFetcher

    @Before
    fun setUp() {
        fetcher = spy()
        val executor = CurrentThreadExecutorService()
        asyncStringFetcher = AsyncStringFetcher(fetcher, executor)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchAsync_future_OK() {
        var actualValue: String? = null
        var actualError: Throwable? = null

        asyncStringFetcher.fetchAsync(
            { value -> actualValue = value },
            { error -> actualError = error }
        )

        verify(fetcher, times(1)).fetch()
        assertThat(actualValue).isEqualTo("foo")
        assertThat(actualError).isNull()
    }

    @Test
    fun fetchAsync_future_NG() {
        doThrow(RuntimeException("ERROR")).whenever(fetcher).fetch()
        var actualValue: String? = null
        var actualError: Throwable? = null

        asyncStringFetcher.fetchAsync(
            { value -> actualValue = value },
            { error -> actualError = error }
        )

        verify(fetcher, times(1)).fetch()
        assertThat(actualValue).isNull()
        assertThat(actualError)
            .isExactlyInstanceOf(RuntimeException::class.java)
            .hasMessage("ERROR")
    }
}