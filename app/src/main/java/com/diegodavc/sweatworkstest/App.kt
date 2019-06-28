package com.diegodavc.sweatworkstest

import android.app.Application
import com.diegodavc.sweatworkstest.data.network.RequestManager
import com.diegodavc.sweatworkstest.data.network.Services

class App : Application(){

    companion object Global{
        lateinit var services : Services
    }

    override fun onCreate() {
        super.onCreate()

        services = RequestManager().defaultRequestManager
    }

}