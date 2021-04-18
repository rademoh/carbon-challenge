package com.rabiu.carbonchallenge.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rabiu.carbonchallenge.data.dao.UsersDao
import com.rabiu.carbonchallenge.util.getValue
import com.rabiu.carbonchallenge.util.testUserA
import com.rabiu.carbonchallenge.util.testUserB
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersDaoTest : DbTest() {
    private lateinit var usersDao: UsersDao
    private val userA = testUserA.copy()
    private val userB = testUserB.copy()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() {
        usersDao = db.usersDao()

        runBlocking {
            usersDao.insertAll(listOf(userA,userB))
        }
    }

    @Test fun testGetUsers() {
        val list = getValue(usersDao.getAll())
        assertThat(list.size, equalTo(2))

        assertThat(list[0], equalTo(userA))
        assertThat(list[1], equalTo(userB))
    }

    @Test fun testGetUserDetail() {
        assertThat(getValue(usersDao.getUserDetail(userA.id)), equalTo(userA))
    }
}