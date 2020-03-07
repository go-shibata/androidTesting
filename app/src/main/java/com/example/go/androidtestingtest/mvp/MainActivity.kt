package com.example.go.androidtestingtest.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.go.androidtestingtest.R

class MainActivity : AppCompatActivity(), View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = GitHubRepository(LocalDataSource())
        val presenter = ListPresenter(this, repository)
        presenter.getRepositoryList("go-shibata")
    }

    override fun showRepositoryList(list: List<Repo>) {
        list.forEach { println(it) }
    }
}
