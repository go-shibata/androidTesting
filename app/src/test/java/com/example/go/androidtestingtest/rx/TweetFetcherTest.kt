package com.example.go.androidtestingtest.rx

import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TweetFetcherTest {

    @Mock(name = "MockTweetRepository")
    lateinit var repository: TweetRepository
    lateinit var tweetFetcher: TweetFetcher

    @Before
    fun setUp() {
        val tweets = listOf(
            Tweet("hello", 1),
            Tweet("from", 2),
            Tweet("world", 3)
        )
        whenever(repository.getRecentTweets()).thenReturn(Single.just(tweets))

        tweetFetcher = TweetFetcher(
            Schedulers.trampoline(),
            Schedulers.trampoline(),
            repository
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun recents() {
        tweetFetcher.getRecentTweets(
            onSuccess = {
                assertThat(it)
                    .extracting("tweet", String::class.java)
                    .containsExactly("hello", "from", "world")
            },
            onError = {}
        )
    }
}