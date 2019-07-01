package com.diegodavc.sweatworkstest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive

@Entity(tableName = "user_table")
data class User(
    val gender: String,
    @PrimaryKey @ColumnInfo(name = "_id") val email: String,
    val phone: String,
    val cell: String,
    val nat: String,
    val name: String,
    val location: String,
    val dob: String,
    val picture: String
){

    fun location(): JsonObject = JsonParser().parse(location).asJsonObject
    fun dob(): JsonObject = JsonParser().parse(dob).asJsonObject
    fun picture(): JsonObject = JsonParser().parse(picture).asJsonObject
}





