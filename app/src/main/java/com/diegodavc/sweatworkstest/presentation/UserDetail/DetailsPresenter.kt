package com.diegodavc.sweatworkstest.presentation.UserDetail

import com.diegodavc.sweatworkstest.data.UserDataSource
import com.diegodavc.sweatworkstest.data.UserRepository
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.presentation.BasePresenter
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val repository: UserRepository)
    : BasePresenter<DetailContract.View>,
    DetailContract.Presenter {

    private var view: DetailContract.View? = null


    override fun setView(view: DetailContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    override fun isSaved(user: User) {
        repository.isSavedUser(user.email, object : UserDataSource.LoadSavedUsersCallback {
            override fun onUsersLoaded(users: List<User>) {
                view?.userSaved()
            }

            override fun onDataNotAvailable() {
                view?.userDeleted()
            }
        })
    }

    override fun saveUser(user: User) {
        repository.saveUser(user)
        view?.userSaved()
    }

    override fun deleteUser(user: User) {
        repository.deleteUser(user, object : UserDataSource.DeleteUserCallback {
            override fun onDelete() {
                view?.userDeleted()
            }
        })
    }

}