package com.diegodavc.sweatworkstest.presentation.UserDetail

import com.diegodavc.sweatworkstest.data.model.User

interface DetailContract {

    interface View{
        fun userSaved()
        fun userDeleted()
    }

    interface Presenter{
        fun saveUser(user: User)
        fun deleteUser(user: User)
        fun isSaved(user: User)
    }

}