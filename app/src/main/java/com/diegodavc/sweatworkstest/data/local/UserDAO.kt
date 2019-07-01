package com.diegodavc.sweatworkstest.data.local

import android.database.Cursor
import androidx.room.*
import com.diegodavc.sweatworkstest.data.model.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertUser(user: User): Completable

    @Query("SELECT * FROM user_table") fun getAllUsers(): Single<List<User>>

    @Query("SELECT * FROM user_table WHERE name LIKE :name") fun getUsers(name: String): Cursor

    @Query("SELECT * FROM user_table WHERE _id = :email") fun isSavedUser(email : String): Single<List<User>>

    @Delete fun deleteUser(user: User): Completable

    @Query("DELETE FROM user_table") fun deleteAll(): Completable
}