package com.example.go.androidtestingtest.rx

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class TweetRepositoryTest {

    lateinit var repository: TweetRepository

    @Before
    fun setUp() {
        repository = TweetRepository()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getRecentTweets() {
        val list = repository.getRecentTweets()
            .test()
            .await()
            .values()[0]

        assertThat(list)
            .extracting("tweet", String::class.java)
            .containsExactly("hello", "from", "world")
    }
}