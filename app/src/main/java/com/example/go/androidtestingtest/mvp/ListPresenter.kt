package com.example.go.androidtestingtest.mvp

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ListPresenter(
    private val view: View,
    private val repository: GitHubRepository
) : Presenter {
    override fun getRepositoryList(user: String) {
        repository.listRepos(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { view.showRepositoryList(it) }
    }
}