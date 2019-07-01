package com.diegodavc.sweatworkstest.data

import com.diegodavc.sweatworkstest.data.local.UserDAO
import com.diegodavc.sweatworkstest.data.local.UserLocalDataSource
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.data.network.Services
import com.diegodavc.sweatworkstest.data.network.UserRemoteDataSource

class UserRepository(
     userDAO: UserDAO,
     services: Services
) : UserDataSource {

    private val localDataSource: UserLocalDataSource = UserLocalDataSource(userDAO)
    private val remoteDataSource: UserRemoteDataSource = UserRemoteDataSource(services)

    override fun isSavedUser(email: String, callback: UserDataSource.LoadSavedUsersCallback) {
        localDataSource.isSavedUser(email, callback)
    }

    override fun getSuggestions(query: String, callback: UserDataSource.LoadSuggestionCallback) {
        localDataSource.getSuggestions(query, callback)
    }

    override fun getSavedUsers(callback: UserDataSource.LoadSavedUsersCallback) {
        localDataSource.getSavedUsers(callback)
    }

    override fun getUsers(seed: String?, page: Int, callback: UserDataSource.LoadUsersCallback) {
        remoteDataSource.getUsers(seed, page, callback)
    }

    override fun saveUser(user: User) {
        localDataSource.saveUser(user)
    }

    override fun deleteAllUsers(callback: UserDataSource.DeleteUserCallback) {
        localDataSource.deleteAllUsers(callback)
    }

    override fun deleteUser(user: User, callback: UserDataSource.DeleteUserCallback) {
        localDataSource.deleteUser(user, callback)
    }
}