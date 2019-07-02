package com.diegodavc.sweatworkstest.di

import android.app.Application
import androidx.room.Room
import com.diegodavc.sweatworkstest.BuildConfig
import com.diegodavc.sweatworkstest.data.UserDataSource
import com.diegodavc.sweatworkstest.data.local.SweatworkDatabase
import com.diegodavc.sweatworkstest.data.local.UserDAO
import com.diegodavc.sweatworkstest.data.local.UserLocalDataSource
import com.diegodavc.sweatworkstest.data.network.Services
import com.diegodavc.sweatworkstest.data.network.UserRemoteDataSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class UserRepositoryModule {

    @Singleton
    @Provides
    @Local
    fun provideUserLocalDataSource(userDAO: UserDAO): UserLocalDataSource = UserLocalDataSource(userDAO)

    @Singleton
    @Provides
    @Remote
    fun provideUserRemoteDataSource(services: Services): UserRemoteDataSource = UserRemoteDataSource(services)

    @Singleton
    @Provides
    fun provideRoomDb(application : Application): SweatworkDatabase
        = Room.databaseBuilder(application.applicationContext,
        SweatworkDatabase::class.java, "SweatworkDatabase")
        .build()

    @Singleton
    @Provides
    fun provideUserDao(db : SweatworkDatabase) : UserDAO = db.userDAO()

    @Singleton
    @Provides
    fun provideServices(retrofit: Retrofit) : Services = retrofit.create(Services::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .create()

        var builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)

        builder.addConverterFactory(GsonConverterFactory.create(gson))
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        builder.client(okHttpClient)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .readTimeout(12, TimeUnit.SECONDS)
            .connectTimeout(12, TimeUnit.SECONDS)

        //For adding logs of APIs requests & responses
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)

        //General interceptor
        builder.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()

                var requestBuilder = original.newBuilder()
                    .method(original.method, original.body)
                return chain.proceed(requestBuilder.build())
            }
        })

        return builder.build()
    }

}