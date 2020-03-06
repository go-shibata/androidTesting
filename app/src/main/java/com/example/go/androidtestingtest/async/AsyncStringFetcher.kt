package com.example.go.androidtestingtest.async

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AsyncStringFetcher(
    private val fetcher: StringFetcher,
    private val executor: ExecutorService = Executors.newCachedThreadPool()
) {
    fun fetchAsync(
        onSuccess: (values: String) -> Unit,
        onFailure: (error: Throwable) -> Unit
    ) {
        executor.submit {
            try {
                val value = fetcher.fetch()
                onSuccess(value)
            } catch (error: Throwable) {
                onFailure(error)
            }
        }
    }
}