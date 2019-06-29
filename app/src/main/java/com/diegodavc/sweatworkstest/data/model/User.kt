package com.diegodavc.sweatworkstest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject

@Entity(tableName = "user_table")
data class User(
    val gender: String,
    val email: String,
    val phone: String,
    val cell: String,
    val nat: String,
    val name: JsonObject,
    val location: JsonObject,
    val login: JsonObject,
    val dob: JsonObject,
    val registered: JsonObject,
    @PrimaryKey val id: JsonObject,
    val picture: JsonObject
)



