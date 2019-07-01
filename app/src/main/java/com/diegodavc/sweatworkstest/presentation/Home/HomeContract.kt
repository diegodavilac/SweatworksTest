package com.diegodavc.sweatworkstest.presentation.Home

import android.database.Cursor
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.data.network.model.UserResponse

interface HomeContract {

    interface View{
        fun loadUsers(users: List<UserResponse>)
        fun loadSavedUsers(users: List<User>)
        fun hideSavedUser()
        fun loadSuggestions(cursor: Cursor)
    }

    interface Presenter{
        fun getSuggestions(query: String)

        fun getUsers()

        fun deleteUser(user: User)

        fun deleteAll()

        fun getSavedUsers()
    }
}