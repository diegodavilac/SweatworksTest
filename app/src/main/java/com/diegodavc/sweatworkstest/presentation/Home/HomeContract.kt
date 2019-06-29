package com.diegodavc.sweatworkstest.presentation.Home

import com.diegodavc.sweatworkstest.data.model.User

interface HomeContract {

    interface View{
        fun loadUsers(users: List<User>)
        fun loadSavedUsers(users: List<User>)
        fun refreshSavedUsers()
        fun hideSavedUser()
    }

    interface Presenter{
        fun getUsers()

        fun deleteUser(user: User)

        fun deleteAll()

        fun getSavedUsers()
    }
}