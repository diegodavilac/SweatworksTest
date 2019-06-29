package com.diegodavc.sweatworkstest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diegodavc.sweatworkstest.data.model.User

@Database(entities = [User::class], version = 1)
abstract class SweatworkDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object{
        private var INSTANCE: SweatworkDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): SweatworkDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SweatworkDatabase::class.java, "SweatworkDatabase.db")
                        .build()
                }
                return INSTANCE!!
            }
        }
    }

}