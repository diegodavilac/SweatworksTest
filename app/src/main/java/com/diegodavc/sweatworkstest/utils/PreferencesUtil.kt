package com.diegodavc.sweatworkstest.utils


import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesUtil @Inject constructor(val mPreferences: SharedPreferences){
    companion object{
        const val SHARED_PREFERENCES = "SWEATWORKS_TEST"
    }
    private val SEED = "seed"


    fun getSeed() : String
        = mPreferences.getString(SEED, "")!!

    fun saveSeed(seed : String){
        val editor = mPreferences.edit()
        editor.putString(SEED, seed)
        editor.apply()
    }



}