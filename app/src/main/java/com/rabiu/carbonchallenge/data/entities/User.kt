package com.rabiu.carbonchallenge.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity( tableName = "users")
data class User (
    @PrimaryKey
    val id: String,
    val email : String,
    val firstName : String,
    val lastName : String,
    val picture : String,
    val title : String,
    val phone : String?,
    val location : Location?

)


