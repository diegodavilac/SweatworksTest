package com.diegodavc.sweatworkstest.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.diegodavc.sweatworkstest.data.model.User

@Database(entities = [User::class], version = 1)
abstract class SweatworkDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO


}