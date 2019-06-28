package com.diegodavc.sweatworkstest.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtil(context: Context){
    private val SHARED_PREFERENCES = "SWEATWORKS_TEST"
    private val SEED = "seed"

    companion object{
        lateinit var self: PreferencesUtil
    }

    private var mPreferences: SharedPreferences

    init {
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        self = this
    }

    fun getSeed() : String
        = mPreferences.getString(SEED, "")!!

    fun saveSeed(seed : String){
        val editor = mPreferences.edit()
        editor.putString(SEED, seed)
        editor.apply()
    }



}