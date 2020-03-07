package com.example.go.androidtestingtest.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<Repos>>
}