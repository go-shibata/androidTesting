package com.example.go.androidtestingtest.room

import android.os.Build
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(Enclosed::class)
class RepositoryLocalDataSourceEnclosedTest {

    abstract class DBTest {
        lateinit var repositoryLocalDataSource: RepositoryLocalDataSource

        @Before
        fun setUpParent() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val db = Room
                .databaseBuilder(context, AppDatabase::class.java, "DB")
                .allowMainThreadQueries() // just for test
                .build()
            repositoryLocalDataSource = RepositoryLocalDataSource(db)
        }

        @After
        fun tearDownParent() {
        }
    }

    @Config(sdk = [Build.VERSION_CODES.P])
    @RunWith(AndroidJUnit4::class)
    class BlankRecord : DBTest() {
        @Test
        fun insertAll_successfullt_persist_record() {
            val owner = "warahikobi"
            repositoryLocalDataSource.insertAll(
                Repository(0, "hello", "hello", owner)
            )
            val list = repositoryLocalDataSource.findByOwner(owner)
            assertThat(list).hasSize(1)
        }
    }

    @Config(sdk = [Build.VERSION_CODES.P])
    @RunWith(AndroidJUnit4::class)
    class RecordsPrepared : DBTest() {
        @Before
        fun setUp() {
            repositoryLocalDataSource.insertAll(
                Repository(0, "hello", "hello", "warahikobi"),
                Repository(1, "world", "world", "warahikobi"),
                Repository(2, "yay", "yay", "hanamogera")
            )
        }

        @Test
        fun findByOwner_givenWarahikobi_returnsSizeCount2() {
            val list = repositoryLocalDataSource.findByOwner("warahikobi")
            assertThat(list).hasSize(2)
        }

        @Test
        fun findByOwner_givenHanamogera_returnsSizeCount1() {
            val list = repositoryLocalDataSource.findByOwner("hanamogera")
            assertThat(list).hasSize(1)
        }
    }
}