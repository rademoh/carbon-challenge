package com.rabiu.carbonchallenge.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rabiu.carbonchallenge.api.DummyApiService
import com.rabiu.carbonchallenge.data.AppDatabase
import com.rabiu.carbonchallenge.data.dao.UsersDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class UserRepositoryTest {
    private lateinit var repository: UserRepository
    private val dao = mock(UsersDao::class.java)
    private val service = mock(DummyApiService::class.java)
    private val remoteDataSource = UsersRemoteData(service)


    private val Id = "1Lkk06cOUCkiAsUXFLMN"
    private val limit = 50
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun init() {
        val db = mock(AppDatabase::class.java)
        `when`(db.usersDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = UserRepository(dao, remoteDataSource)
    }

    @Test
    fun loadUsersListFromNetwork() {
        runBlocking {
            repository.fetchUsers(limit)
            verify(dao, never()).getUserDetail(Id)
            verifyZeroInteractions(dao)
        }
    }


}