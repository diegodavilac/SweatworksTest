package com.diegodavc.sweatworkstest.data.network.model

import com.diegodavc.sweatworkstest.data.model.Info
import com.diegodavc.sweatworkstest.data.model.User

open class UserResponse(val results : List<User> ,
                    val info : Info)