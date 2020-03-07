package com.example.go.androidtestingtest.mvp

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListPresenterTest {

    private val view: View = mock()
    private val repository: GitHubRepository = mock {
        on { listRepos(any()) } doReturn Single.just(
            listOf(
                Repo(""), Repo(""), Repo("")
            )
        )
    }
    private val presenter = ListPresenter(view, repository)

    @get:Rule
    val rule = RxImmediateSchedulerRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getRepositoryList_HasSize3() {
        presenter.getRepositoryList("go-shibata")
        argumentCaptor<List<Repo>>().apply {
            verify(view, times(1)).showRepositoryList(capture())
            Assertions.assertThat(firstValue).hasSize(3)
        }
    }
}