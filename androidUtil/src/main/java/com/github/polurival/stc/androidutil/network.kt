package com.github.polurival.stc.androidutil

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * todo Этого может быть недостаточно, так как сеть может быть, а интернета нет (закончился =))
 *
 * @author Юрий Польщиков on 23.10.2021
 */
fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
            val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
            return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        else -> {
            val networks = cm.allNetworks
            for (n in networks) {
                val nInfo = cm.getNetworkInfo(n)
                if (nInfo != null && nInfo.isConnected) return true
            }
        }
    }
    return false
}
