package com.example.data.broadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


class ConnectivityBroadcastReceiver  : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {

        }
        else
        {
           // Toast.makeText(context,"Not connection",Toast.LENGTH_LONG).show()

        }
    }
}