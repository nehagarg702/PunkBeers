package com.example.punkbeers.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.punkbeers.PunkBeerApplication

/****
 * Class to check the network connection
 */
object UtilityMethods {
    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = PunkBeerApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting)
        }
}