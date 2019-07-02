package com.diegodavc.sweatworkstest.presentation.UserDetail

import com.diegodavc.sweatworkstest.di.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class UserDetailModule {

    @ActivityScoped
    @Binds
    abstract fun detailsPresenter(presenter: DetailsPresenter) : DetailContract.Presenter
}