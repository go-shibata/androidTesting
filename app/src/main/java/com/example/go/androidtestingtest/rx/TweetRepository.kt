package com.example.go.androidtestingtest.rx

import io.reactivex.Single
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TweetRepository {
    private val executor: Executor = Executors.newSingleThreadExecutor()

    fun getRecentTweets(): Single<List<Tweet>> {
        return Single.create { emitter ->
            try {
                // some I/O task
                val tweets = listOf(
                    Tweet("hello", 1),
                    Tweet("from", 2),
                    Tweet("world", 3)
                )
                emitter.onSuccess(tweets)
            } catch (error: Throwable) {
                emitter.onError(error)
            }
        }
    }
}