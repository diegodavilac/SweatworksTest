package com.diegodavc.sweatworkstest.presentation.UserDetail

import dagger.Binds
import dagger.Module

@Module
abstract class UserDetailModule {

    @Binds
    abstract fun detailsPresenter(presenter: DetailsPresenter) : DetailContract.Presenter
}