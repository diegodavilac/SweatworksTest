package com.diegodavc.sweatworkstest.di

import android.app.Application
import com.diegodavc.sweatworkstest.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface AppComponent {


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : AppComponent.Builder

        fun build() : AppComponent
    }

    fun inject(app: App)
}