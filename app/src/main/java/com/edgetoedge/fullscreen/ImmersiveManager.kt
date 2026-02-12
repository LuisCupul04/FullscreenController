package com.edgetoedge.fullscreen

import android.content.Context

object ImmersiveManager {

    fun applyImmersive(context: Context, packageName: String) {

        val command =
            "settings put global policy_control immersive.full=$packageName"

        when {
            RootExecutor.isRootAvailable() ->
                RootExecutor.execute(command)

            ShizukuExecutor.isAvailable() ->
                ShizukuExecutor.execute(command)

            else -> {
                // Fallback ADB
                val adbCommand =
                    "adb shell $command"
                android.widget.Toast
                    .makeText(context, adbCommand, android.widget.Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
