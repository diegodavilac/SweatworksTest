package com.diegodavc.sweatworkstest


import com.diegodavc.sweatworkstest.data.UserRepository
import com.diegodavc.sweatworkstest.di.DaggerAppComponent
import com.diegodavc.sweatworkstest.utils.PreferencesUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object Global{
        lateinit var mPreferences : PreferencesUtil
    }

    @Inject lateinit var userRepository : UserRepository

    override fun onCreate() {
        super.onCreate()

        mPreferences = PreferencesUtil(this)

    }



}