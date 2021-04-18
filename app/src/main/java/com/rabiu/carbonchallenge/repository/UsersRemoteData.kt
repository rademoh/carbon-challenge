package com.rabiu.carbonchallenge.repository

import com.rabiu.carbonchallenge.api.BaseDataSource
import com.rabiu.carbonchallenge.api.DummyApiService


class UsersRemoteData constructor(val api : DummyApiService): BaseDataSource(){

     suspend fun fetchUsers(limit : Int) = getResult { api.fetchUsersList(limit) }

     suspend fun fetchUserDetail(id : String) = getResult { api.fetchUserDetails(id) }

}