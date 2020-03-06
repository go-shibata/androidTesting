package com.example.go.androidtestingtest.room

import android.os.Build
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1, Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class RepositoryLocalDataSourceTest {

    lateinit var repositoryLocalDataSource: RepositoryLocalDataSource

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val db = Room
            .databaseBuilder(context, AppDatabase::class.java, "DB")
            .allowMainThreadQueries() // just for test
            .build()
        repositoryLocalDataSource = RepositoryLocalDataSource(db)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun insertAll_finishesSuccessfully() {
        val owner = "warahikobi"
        repositoryLocalDataSource.insertAll(
            Repository(1, "hello", "hello", owner),
            Repository(2, "world", "world", owner)
        )
        val list = repositoryLocalDataSource.findByOwner(owner)
        assertThat(list).hasSize(2)
    }

    @Test
    fun findByOwner_expectsEmpty() {
        val owner = "warahikobi"
        val list = repositoryLocalDataSource.findByOwner(owner)
        assertThat(list).isEmpty()
    }
}