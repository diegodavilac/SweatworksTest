package com.diegodavc.sweatworkstest.presentation.Home

import android.database.Cursor
import com.diegodavc.sweatworkstest.App
import com.diegodavc.sweatworkstest.data.UserDataSource
import com.diegodavc.sweatworkstest.data.UserRepository
import com.diegodavc.sweatworkstest.data.model.Info
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.data.network.model.UserResponse

class HomePresenter(val repository: UserRepository,val view: HomeContract.View) : HomeContract.Presenter{

    var pageNumber : Int = 0

    override fun getUsers() {
        val seed = App.mPreferences.getSeed()
        repository.getUsers(seed, pageNumber, object : UserDataSource.LoadUsersCallback {
            override fun onUsersLoaded(users: List<UserResponse>) {
                view.loadUsers(users)
            }

            override fun onSeedReceived(info: Info) {
                if (seed.isEmpty()){
                    App.mPreferences.saveSeed(info.seed)
                }

                pageNumber = info.page +1
            }
        })
    }

    override fun deleteUser(user: User) {
        repository.deleteUser(user, object : UserDataSource.DeleteUserCallback{
            override fun onDelete() {
            }
        })
    }

    override fun deleteAll() {
        repository.deleteAllUsers(object : UserDataSource.DeleteUserCallback{
            override fun onDelete() {
                view.hideSavedUser()
            }
        })
    }

    override fun getSavedUsers() {
        repository.getSavedUsers(object : UserDataSource.LoadSavedUsersCallback{
            override fun onUsersLoaded(users: List<User>) {
                view.loadSavedUsers(users)
            }

            override fun onDataNotAvailable() {
                view.hideSavedUser()
            }
        })
    }

    override fun getSuggestions(query: String) {
        repository.getSuggestions(query, object : UserDataSource.LoadSuggestionCallback{
            override fun onUsersLoaded(users: Cursor) {
                view.loadSuggestions(users)
            }
        })
    }
}