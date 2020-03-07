package com.example.go.androidtestingtest.mvp

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.stubbing.Answer

class GitHubRepositoryTest {

    private val localDataSource = mock<LocalDataSource>(
        // to detect unintended calls
        defaultAnswer = Answer { throw RuntimeException() }
    )
    lateinit var gitGubRepository: GitHubRepository

    @Before
    fun setUp() {
        doReturn(Single.just(listOf(Repo(""), Repo(""), Repo(""))))
            .whenever(localDataSource).listRepos(any())
        gitGubRepository = GitHubRepository(localDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun listRepos_confirmCall() {
        gitGubRepository.listRepos("go-shibata")
            .test()
            .assertComplete()
            .assertNoErrors()
        verify(localDataSource, times(1)).listRepos(eq("go-shibata"))
        verifyNoMoreInteractions(localDataSource) // to detect unintended calls
    }
}