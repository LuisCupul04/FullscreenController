package com.edgetoedge.fullscreen

import java.io.DataOutputStream
import java.io.File

object RootExecutor {

    fun isRootAvailable(): Boolean {
        val paths = arrayOf(
            "/system/bin/su",
            "/system/xbin/su"
        )
        return paths.any { File(it).exists() }
    }

    fun execute(command: String) {
        try {
            val process = Runtime.getRuntime().exec("su")
            val os = DataOutputStream(process.outputStream)
            os.writeBytes("$command\n")
            os.writeBytes("exit\n")
            os.flush()
            process.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
