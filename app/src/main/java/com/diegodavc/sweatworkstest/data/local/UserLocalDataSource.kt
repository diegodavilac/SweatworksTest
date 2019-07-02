package com.diegodavc.sweatworkstest.data.local

import com.diegodavc.sweatworkstest.data.UserDataSource
import com.diegodavc.sweatworkstest.data.model.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor (val userDAO: UserDAO): UserDataSource{

    override fun isSavedUser(email: String, callback: UserDataSource.LoadSavedUsersCallback) {
        userDAO.isSavedUser(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { if (it.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onUsersLoaded(it)
            }
            .subscribe()
    }

    override fun getSuggestions(query: String, callback: UserDataSource.LoadSuggestionCallback) {
        Single.just(query)
            .subscribeOn(Schedulers.io())
            .map {
                userDAO.getUsers(query)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                callback.onUsersLoaded(it)
            }
            .subscribe()
    }

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