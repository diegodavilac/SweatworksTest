package com.diegodavc.sweatworkstest.data.local

import com.diegodavc.sweatworkstest.data.UserDataSource
import com.diegodavc.sweatworkstest.data.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserLocalDataSource (val userDAO: UserDAO): UserDataSource{

    override fun getSavedUsers(callback: UserDataSource.LoadSavedUsersCallback) {
        userDAO.getAllUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { if (it.isEmpty())
                callback.onDataNotAvailable()
            else
                callback.onUsersLoaded(it)
            }
            .subscribe()
    }

    override fun getUsers(seed : String?, page: Int, callback: UserDataSource.LoadUsersCallback) {

    }

    override fun saveUser(user: User) {
        userDAO.insertUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun deleteAllUsers(callback: UserDataSource.DeleteUserCallback) {
       userDAO.deleteAll()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .doOnComplete{
               callback.onDelete()
           }
           .subscribe()
    }

    override fun deleteUser(user: User, callback: UserDataSource.DeleteUserCallback) {
        userDAO.deleteUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete{
                callback.onDelete()
            }
            .subscribe()
    }
}