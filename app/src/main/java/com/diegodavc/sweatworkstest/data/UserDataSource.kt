package com.diegodavc.sweatworkstest.data

import com.diegodavc.sweatworkstest.data.model.Info
import com.diegodavc.sweatworkstest.data.model.User

interface UserDataSource {
    interface LoadUsersCallback {

        fun onUsersLoaded(users: List<User>)

        fun onSeedReceived(info: Info)
    }

    interface DeleteUserCallback{
        fun onDelete()
    }

    interface LoadSavedUsersCallback {

        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable()

    }

    fun getUsers(seed: String? = null, page: Int = 1, callback: LoadUsersCallback)

    fun getSavedUsers(callback: LoadSavedUsersCallback)

    fun saveUser(user: User)

    fun deleteAllUsers(callback: DeleteUserCallback)

    fun deleteUser(user: User, callback: DeleteUserCallback)
}