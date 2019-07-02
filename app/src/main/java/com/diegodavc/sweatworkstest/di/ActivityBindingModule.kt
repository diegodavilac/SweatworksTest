package com.diegodavc.sweatworkstest.di

import com.diegodavc.sweatworkstest.presentation.Home.HomeModule
import com.diegodavc.sweatworkstest.presentation.Home.MainActivity
import com.diegodavc.sweatworkstest.presentation.UserDetail.UserDetailActivity
import com.diegodavc.sweatworkstest.presentation.UserDetail.UserDetailModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeActivity(): MainActivity

    @ContributesAndroidInjector(modules = [UserDetailModule::class])
    abstract fun userDetailActivity(): UserDetailActivity

}