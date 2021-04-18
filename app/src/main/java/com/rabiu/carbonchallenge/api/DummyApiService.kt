package com.rabiu.carbonchallenge.api

import com.rabiu.carbonchallenge.data.entities.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DummyApiService {

    @GET("user")
    suspend fun fetchUsersList(@Query("limit") limit : Int):Response<ResultsResponse<User>>

    @GET("user/{id}")
    suspend fun fetchUserDetails(@Path("id") id:String): Response<User>
}