package com.diegodavc.sweatworkstest.di

import android.app.Application
import android.content.Context
import com.diegodavc.sweatworkstest.utils.PreferencesUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun providePreferencesUtil(application: Application): PreferencesUtil
            = PreferencesUtil(application.getSharedPreferences(PreferencesUtil.SHARED_PREFERENCES, Context.MODE_PRIVATE))
}