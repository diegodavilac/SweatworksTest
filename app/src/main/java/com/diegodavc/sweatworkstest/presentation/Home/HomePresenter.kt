package com.diegodavc.sweatworkstest.presentation.Home

import android.database.Cursor
import com.diegodavc.sweatworkstest.App
import com.diegodavc.sweatworkstest.data.UserDataSource
import com.diegodavc.sweatworkstest.data.UserRepository
import com.diegodavc.sweatworkstest.data.model.Info
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.data.network.model.UserResponse
import com.diegodavc.sweatworkstest.presentation.BasePresenter
import com.diegodavc.sweatworkstest.utils.PreferencesUtil
import javax.inject.Inject

class HomePresenter @Inject constructor( val repository: UserRepository) :BasePresenter<HomeContract.View>, HomeContract.Presenter{

    override fun setView(view: HomeContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

    var pageNumber : Int = 0

    private var view : HomeContract.View? = null

    @Inject lateinit var mPreferences : PreferencesUtil

    override fun getUsers() {
        val seed = mPreferences.getSeed()
        repository.getUsers(seed, pageNumber, object : UserDataSource.LoadUsersCallback {
            override fun onUsersLoaded(users: List<UserResponse>) {
                view?.loadUsers(users)
            }

            override fun onSeedReceived(info: Info) {
                if (seed.isEmpty()){
                    mPreferences.saveSeed(info.seed)
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
                view?.hideSavedUser()
            }
        })
    }

    override fun getSavedUsers() {
        repository.getSavedUsers(object : UserDataSource.LoadSavedUsersCallback{
            override fun onUsersLoaded(users: List<User>) {
                view?.loadSavedUsers(users)
            }

            override fun onDataNotAvailable() {
                view?.hideSavedUser()
            }
        })
    }

    override fun getSuggestions(query: String) {
        repository.getSuggestions(query, object : UserDataSource.LoadSuggestionCallback{
            override fun onUsersLoaded(users: Cursor) {
                view?.loadSuggestions(users)
            }
        })
    }
}