package com.diegodavc.sweatworkstest.data.network.model

import com.diegodavc.sweatworkstest.data.model.User
import com.google.gson.Gson
import com.google.gson.JsonObject

data class UserResponse (
    val gender: String,
    val email: String,
    val phone: String,
    val cell: String,
    val nat: String,
    val name: JsonObject,
    val location: JsonObject,
    val dob: JsonObject,
    val picture: JsonObject

){
    fun getFullName(): String = "${name.get("first").asString} ${name.get("last").asString}"

    fun toUser():User
            = User(gender, email,phone, cell, nat, getFullName(), Gson().toJson(location), Gson().toJson(dob), Gson().toJson(picture))
}