package com.example.androidlearningapp.movies.data.listeners

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.androidlearningapp.movies.data.extensions.toast

class PowerStatusReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "android.intent.action.ACTION_POWER_CONNECTED", "android.intent.action.ACTION_POWER_DISCONNECTED" -> context.toast("Power status changed")
        }
    }
}
