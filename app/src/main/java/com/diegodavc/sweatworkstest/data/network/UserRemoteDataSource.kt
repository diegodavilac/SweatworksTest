package com.diegodavc.sweatworkstest.data.network

import com.diegodavc.sweatworkstest.data.UserDataSource
import com.diegodavc.sweatworkstest.data.model.User
import io.reactivex.android.schedulers.AndroidSchedulers

class UserRemoteDataSource(val services: Services) : UserDataSource {

    override fun getSavedUsers(callback: UserDataSource.LoadSavedUsersCallback) {

    }

    override fun getUsers(seed:String? , page: Int, callback: UserDataSource.LoadUsersCallback) {
        services.getUsers(50, seed, page )
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                callback.onSeedReceived(it.info)
                it.results
            }
            .doOnSuccess {
                callback.onUsersLoaded(it)
            }
            .subscribe()


    }

    override fun saveUser(user: User) {

    }

    override fun deleteAllUsers(callback: UserDataSource.DeleteUserCallback) {

    }

    override fun deleteUser(user: User, callback: UserDataSource.DeleteUserCallback) {

    }
}