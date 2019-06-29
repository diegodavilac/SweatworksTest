package com.diegodavc.sweatworkstest.data.local

import androidx.room.*
import com.diegodavc.sweatworkstest.data.model.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertUser(user: User): Completable

    @Query("SELECT * FROM user_table") fun getAllUsers(): Single<List<User>>

    @Delete fun deleteUser(user: User): Completable

    @Query("DELETE FROM user_table") fun deleteAll(): Completable
}