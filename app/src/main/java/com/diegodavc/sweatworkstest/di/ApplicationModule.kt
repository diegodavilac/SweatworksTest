package com.diegodavc.sweatworkstest.di

import android.app.Application
import android.content.Context
import com.diegodavc.sweatworkstest.utils.PreferencesUtil
import com.diegodavc.sweatworkstest.utils.PreferencesUtil.Companion.SHARED_PREFERENCES
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ApplicationModule{

    @Binds
    abstract fun bindContext(application: Application) : Context

}