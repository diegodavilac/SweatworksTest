package com.diegodavc.sweatworkstest.data.network


import com.diegodavc.sweatworkstest.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor;

import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RequestManager() {
    var defaultRequestManager: Services
    private var retrofit: Retrofit

    init {
        retrofit = generateRerofit()
        defaultRequestManager = retrofit.create(Services::class.java)
    }

    private fun generateRerofit(): Retrofit {
        val gson = GsonBuilder().create()
        var client = getOkHttpClient()

        var builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)

        builder.addConverterFactory(GsonConverterFactory.create(gson))
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        builder.client(client)

        return builder.build()
    }


    /**
     * Generates OkHttpClient instance with configured timeouts and auth/logging interceptors
     *
     * @return OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient {
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