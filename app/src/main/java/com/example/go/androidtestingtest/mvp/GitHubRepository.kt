package com.example.go.androidtestingtest.mvp

import io.reactivex.Single

class GitHubRepository(private val localDataSource: LocalDataSource) {
    fun listRepos(user: String): Single<List<Repo>> {
        return localDataSource.listRepos(user)
    }
}