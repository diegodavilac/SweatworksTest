package com.diegodavc.sweatworkstest.presentation.Home

import dagger.Binds
import dagger.Module

@Module
abstract class HomeModule {

    @Binds
    abstract fun homePresenter(presenter: HomePresenter) : HomeContract.Presenter
}