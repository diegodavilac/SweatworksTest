package com.diegodavc.sweatworkstest.data.network

import com.diegodavc.sweatworkstest.data.network.model.UserResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET (".")
    fun  getUsers(@Query("results") results : Int,
                  @Query ("seed") seed: String?,
                  @Query("page") page : Int) : Single<UserResponse>

}