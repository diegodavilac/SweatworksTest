package com.diegodavc.sweatworkstest.presentation.UserDetail

import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.presentation.BasePresenter
import com.diegodavc.sweatworkstest.presentation.BaseView

interface DetailContract {

    interface  View : BaseView<Presenter>{
        fun userSaved()
        fun userDeleted()
    }

    interface Presenter : BasePresenter<View>{
        fun saveUser(user: User)
        fun deleteUser(user: User)
        fun isSaved(user: User)
    }

}