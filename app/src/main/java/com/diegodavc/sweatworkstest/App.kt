package com.diegodavc.sweatworkstest

import android.app.Application
import androidx.room.Database
import com.diegodavc.sweatworkstest.data.local.SweatworkDatabase
import com.diegodavc.sweatworkstest.data.network.RequestManager
import com.diegodavc.sweatworkstest.data.network.Services
import com.diegodavc.sweatworkstest.utils.PreferencesUtil

class App : Application(){

    companion object Global{
        lateinit var services : Services
        lateinit var mPreferences : PreferencesUtil
        lateinit var database: SweatworkDatabase
    }

    override fun onCreate() {
        super.onCreate()

        services = RequestManager().defaultRequestManager
        mPreferences = PreferencesUtil(this)
        database = SweatworkDatabase.getInstance(this)
    }

}