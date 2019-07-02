package com.diegodavc.sweatworkstest.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
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


fun ProgressBar?.showLoader(show: Boolean){
    if(this != null){
        val shortAnimTime = context.resources.getInteger(android.R.integer.config_shortAnimTime)

        val alpha_p = if (show) 1.0f else 0.0f

        if (show && context is Activity) {
            (context as Activity).window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {
            if (context is Activity) {
                (context as Activity).window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        }

        visibility = if (show) View.VISIBLE else View.GONE

        animate().setDuration(shortAnimTime.toLong()).alpha(alpha_p)
            .setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    if (show) visibility = View.VISIBLE else visibility = View.GONE
                }
            })
    }
}