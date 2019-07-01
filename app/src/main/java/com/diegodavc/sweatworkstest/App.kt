package com.diegodavc.sweatworkstest

import android.app.Application
import androidx.room.Database
import com.diegodavc.sweatworkstest.data.local.SweatworkDatabase
import com.diegodavc.sweatworkstest.data.network.RequestManager
import com.diegodavc.sweatworkstest.data.network.Services
import com.diegodavc.sweatworkstest.di.DaggerAppComponent
import com.diegodavc.sweatworkstest.utils.PreferencesUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : Application(){

    companion object Global{
        lateinit var services : Services
        lateinit var mPreferences : PreferencesUtil
        lateinit var database: SweatworkDatabase
    }


    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)


        services = RequestManager().defaultRequestManager
        mPreferences = PreferencesUtil(this)
        database = SweatworkDatabase.getInstance(this)
    }

}