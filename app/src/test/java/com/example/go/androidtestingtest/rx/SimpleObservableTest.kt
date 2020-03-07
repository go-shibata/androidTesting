package com.example.go.androidtestingtest.rx

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.concurrent.TimeUnit

class SimpleObservableTest {

    @Test
    fun observable_returnsRx() {
        val observable = Observable.just("Rx").delay(1, TimeUnit.SECONDS)

        val subscriber = observable.test()
        subscriber.await()
            .assertComplete()
            .assertValue("Rx")
    }

    @Test
    fun observable_containsAll() {
        val listObservable: Observable<String> = listOf("Giants", "Dodgers", "Athletics")
            .toObservable()
            .delay(1, TimeUnit.SECONDS)

        val teams = listObservable.test()
            .await()
            .assertComplete()
            .values()

        assertThat(teams).containsExactly("Giants", "Dodgers", "Athletics")
    }

    @Test
    fun observable_throwsException() {
        val errorObservable: Observable<RuntimeException> =
            Observable.error(RuntimeException("ERROR"))

        errorObservable.test()
            .assertNotComplete()
            .assertError(RuntimeException::class.java)
            .assertErrorMessage("ERROR")
    }
}