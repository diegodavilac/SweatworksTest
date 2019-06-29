package com.diegodavc.sweatworkstest.presentation.UserDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diegodavc.sweatworkstest.R
import com.diegodavc.sweatworkstest.data.model.User
import com.diegodavc.sweatworkstest.utils.loadImage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.item_grid.view.*
import kotlinx.android.synthetic.main.toolbar.*

class UserDetailActivity : AppCompatActivity() {

    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        initToolbar()

        var userString = intent.extras?.getString("user", "")

        if ( userString != null && userString.isNotEmpty()){
            user = Gson().fromJson(userString, User::class.java)
        }

        bindUser()

    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun bindUser(){
        tv_name.text = "${(user.name["first"].asString)} ${(user.name["last"].asString)}"
        tv_email.text = user.email
        tv_phone.text = user.phone

        iv_user.loadImage(this, user.picture["large"].asString)
    }
}
