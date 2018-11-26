package com.example.androidlearningapp.movies.data.api.listeners

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PowerStatusReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "android.intent.action.ACTION_POWER_CONNECTED", "android.intent.action.ACTION_POWER_DISCONNECTED" -> Toast.makeText(context, "Power status changed", Toast.LENGTH_SHORT).show()
        }
    }
}
