package com.diegodavc.sweatworkstest.data.model

import com.google.gson.JsonObject

class User(val gender : String ,
           val email: String,
           val phone: String,
           val cell: String,
           val nat: String,
           val name: JsonObject,
           val location: JsonObject,
           val login: JsonObject,
           val dob: JsonObject,
           val registered: JsonObject,
           val id: JsonObject,
           val picture: JsonObject)



