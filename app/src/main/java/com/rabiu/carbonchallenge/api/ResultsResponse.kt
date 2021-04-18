package com.rabiu.carbonchallenge.api

import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(

    @SerializedName("data")
    val data: List<T>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int

)