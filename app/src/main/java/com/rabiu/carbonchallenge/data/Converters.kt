package com.rabiu.carbonchallenge.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rabiu.carbonchallenge.data.entities.Location



class Converters {

    @TypeConverter
    fun locationToJson(value: Location?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun locationJsonToModel(value: String): Location? {
        return Gson().fromJson(value, Location::class.java) as Location?
    }



}