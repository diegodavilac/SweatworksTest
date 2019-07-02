package com.diegodavc.sweatworkstest.presentation.Home

import com.diegodavc.sweatworkstest.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class HomeModule {

    @ActivityScoped
    @Binds
    abstract fun homePresenter(presenter: HomePresenter) : HomeContract.Presenter
}