package com.diegodavc.sweatworkstest.presentation.UserDetail

import com.diegodavc.sweatworkstest.data.UserDataSource
import com.diegodavc.sweatworkstest.data.UserRepository
import com.diegodavc.sweatworkstest.data.model.User

class DetailsPresenter(private val repository: UserRepository ,
                       private val view: DetailContract.View): DetailContract.Presenter {

    override fun isSaved(user: User) {
        repository.isSavedUser(user.email, object : UserDataSource.LoadSavedUsersCallback{
            override fun onUsersLoaded(users: List<User>) {
                view.userSaved()
            }

            override fun onDataNotAvailable() {
                view.userDeleted()
            }
        })
    }

    override fun saveUser(user: User) {
        repository.saveUser(user)
        view.userSaved()
    }

    override fun deleteUser(user: User) {
        repository.deleteUser(user, object : UserDataSource.DeleteUserCallback{
            override fun onDelete() {
                view.userDeleted()
            }
        })
    }

}