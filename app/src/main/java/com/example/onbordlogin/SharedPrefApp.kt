package com.example.onbordlogin

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class SharedPrefApp {

    lateinit var sharepreferences: SharedPreferences




    fun saveISLogged_IN(context: Context, isLoggedin:Boolean) {
        sharepreferences = PreferenceManager
            .getDefaultSharedPreferences(context)
        val editor = sharepreferences.edit()
        editor.putBoolean("IS_LOGIN", isLoggedin)
        editor.commit()
    }


    fun getISLogged_IN(context:Context):Boolean {
        sharepreferences = PreferenceManager
            .getDefaultSharedPreferences(context)
        return sharepreferences.getBoolean("IS_LOGIN", false)
    }

    companion object {


        fun getInstance(): SharedPrefApp? {

            var instance:  SharedPrefApp? = null

            if (instance == null)
            {
                synchronized (SharedPrefApp::class.java) {
                    instance = SharedPrefApp()
                }
            }
            return instance
        }
    }



}