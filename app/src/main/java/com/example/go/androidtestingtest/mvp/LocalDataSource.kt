package com.example.go.androidtestingtest.mvp

import io.reactivex.Single
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class LocalDataSource {
    private val executor: Executor = Executors.newSingleThreadExecutor()

    fun listRepos(user: String): Single<List<Repo>> {
        return Single.create { emitter ->
            executor.execute {
                try {
                    // some I/O task
                    val repos = listOf(Repo(user))
                    emitter.onSuccess(repos)
                } catch (error: Throwable) {
                    emitter.onError(error)
                }
            }
        }
    }
}