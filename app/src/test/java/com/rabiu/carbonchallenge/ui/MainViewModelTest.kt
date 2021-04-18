package com.rabiu.carbonchallenge.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rabiu.carbonchallenge.repository.UserRepository
import com.rabiu.carbonchallenge.ui.userList.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class MainViewModelTest {

    private val limit = 50
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = mock(UserRepository::class.java)
    private var viewModel = MainViewModel(repository)

    @Test
    fun testNull() {
        assertThat(viewModel.limit, notNullValue())
        verify(repository, never()).fetchUsers(limit)
    }

    @Test
    fun doFetchWithObservers() {
        viewModel.limit = limit
        verify(repository, never()).fetchUsers(limit)
    }



}