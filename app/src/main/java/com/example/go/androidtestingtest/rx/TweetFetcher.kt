package com.example.go.androidtestingtest.rx

import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy

class TweetFetcher(
    private val subscribeScheduler: Scheduler,
    private val observableScheduler: Scheduler,
    private val repository: TweetRepository
) {
    fun getRecentTweets(onSuccess: (List<Tweet>) -> Unit, onError: (Throwable) -> Unit) {
        repository.getRecentTweets()
            .subscribeOn(subscribeScheduler)
            .observeOn(observableScheduler)
            .subscribeBy(
                onSuccess = onSuccess,
                onError = onError
            )
    }
}