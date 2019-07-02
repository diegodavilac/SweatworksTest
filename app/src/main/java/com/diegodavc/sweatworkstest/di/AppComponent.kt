package com.diegodavc.sweatworkstest.di

import android.app.Application
import com.diegodavc.sweatworkstest.App
import com.diegodavc.sweatworkstest.data.UserRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
                            UserRepositoryModule::class,
                            ApplicationModule::class,
                            ActivityBindingModule::class,
                            AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }


}