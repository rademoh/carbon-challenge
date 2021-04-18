package com.rabiu.carbonchallenge.repository

import com.rabiu.carbonchallenge.data.dao.UsersDao
import com.rabiu.carbonchallenge.data.resultLiveData


open class UserRepository constructor(private val db : UsersDao, private val api : UsersRemoteData) {

    fun fetchUsers(limit : Int) = resultLiveData(databaseQuery = {db.getAll()}, networkCall = {api.fetchUsers(limit)}, saveCallResult = {db.insertAll(it.data)} )

    fun getUserDetail(id : String) = resultLiveData( databaseQuery = {db.getUserDetail(id)} , networkCall = {api.fetchUserDetail(id)}, saveCallResult = {db.insert(it)})
}