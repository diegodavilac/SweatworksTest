package com.diegodavc.sweatworkstest.data.network.model

import com.diegodavc.sweatworkstest.data.model.Info
import com.diegodavc.sweatworkstest.data.model.User

open class UserListResponse(val results : List<UserResponse>,
                            val info : Info)