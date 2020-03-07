package com.example.go.androidtestingtest.retrofit

import io.reactivex.rxkotlin.subscribeBy
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun main() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val gitHubService = retrofit.create(GitHubService::class.java)
    val githubRemoteDataSource = GitHubRemoteDataSource(gitHubService)
    githubRemoteDataSource.listRepos("go-shibata")
        .subscribeBy { list ->
            list.forEach { println(it) }
        }
}