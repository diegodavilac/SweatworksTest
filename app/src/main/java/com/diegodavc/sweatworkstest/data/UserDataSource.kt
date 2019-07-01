package com.diegodavc.sweatworkstest.data

import android.database.Cursor
import com.diegodavc.sweatworkstest.data.model.Info
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.data.network.model.UserResponse

interface UserDataSource {
    interface LoadUsersCallback {

        fun onUsersLoaded(users: List<UserResponse>)

        fun onSeedReceived(info: Info)
    }

    interface DeleteUserCallback{
        fun onDelete()
    }

    interface LoadSavedUsersCallback {

        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable()

    }

    interface LoadSuggestionCallback {

        fun onUsersLoaded(users: Cursor)

    }

    fun getSuggestions(query: String, callback: LoadSuggestionCallback)

    fun getUsers(seed: String? = null, page: Int = 1, callback: LoadUsersCallback)

    fun getSavedUsers(callback: LoadSavedUsersCallback)

    fun saveUser(user: User)

    fun deleteAllUsers(callback: DeleteUserCallback)

    fun deleteUser(user: User, callback: DeleteUserCallback)

    fun isSavedUser(email: String, callback: LoadSavedUsersCallback)
}