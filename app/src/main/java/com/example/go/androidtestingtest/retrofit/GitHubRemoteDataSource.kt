package com.example.go.androidtestingtest.retrofit

import io.reactivex.Single

class GitHubRemoteDataSource(private val gitHubService: GitHubService) {
    fun listRepos(user: String): Single<List<Repos>> {
        return gitHubService.listRepos(user)
    }
}