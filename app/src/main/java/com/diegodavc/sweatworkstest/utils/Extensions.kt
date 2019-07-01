package com.diegodavc.sweatworkstest.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.diegodavc.sweatworkstest.R
import kotlinx.android.synthetic.main.activity_user_detail.*

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImage(context : Context, url: String){
    val requestOptions = RequestOptions()
    requestOptions.placeholder(R.drawable.ic_broken_image)
    requestOptions.error(R.drawable.ic_broken_image)
    Glide.with(context).load(url).apply(requestOptions).into(this)
}
