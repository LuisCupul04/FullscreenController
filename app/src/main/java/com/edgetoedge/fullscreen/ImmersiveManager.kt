package com.edgetoedge.fullscreen

import android.content.Context
<<<<<<< HEAD
=======
import android.widget.Toast
>>>>>>> 1044f2a (v3.0)

object ImmersiveManager {

    fun applyImmersive(context: Context, packageName: String) {

        val command =
            "settings put global policy_control immersive.full=$packageName"

<<<<<<< HEAD
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
=======
        if (!RootExecutor.isRootAvailable() &&
            !ShizukuExecutor.isAvailable()
        ) {
            Toast.makeText(
                context,
                "Necesitas Root o Shizuku",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        CommandExecutor.execute(command)
    }

    fun removeImmersive(context: Context) {

        val command = "settings delete global policy_control"

        if (!RootExecutor.isRootAvailable() &&
            !ShizukuExecutor.isAvailable()
        ) {
            Toast.makeText(
                context,
                "Necesitas Root o Shizuku",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        CommandExecutor.execute(command)
>>>>>>> 1044f2a (v3.0)
    }
}
