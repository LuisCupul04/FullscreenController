package com.edgetoedge.fullscreen

import java.io.DataOutputStream
import java.io.File

object RootExecutor {

    fun isRootAvailable(): Boolean {
<<<<<<< HEAD
        val paths = arrayOf(
            "/system/bin/su",
            "/system/xbin/su"
        )
        return paths.any { File(it).exists() }
=======
        return try {
            val process = Runtime.getRuntime()
                .exec(arrayOf("su", "-c", "id"))
            process.waitFor() == 0
        } catch (e: Exception) {
            false
        }
>>>>>>> 1044f2a (v3.0)
    }

    fun execute(command: String) {
        try {
<<<<<<< HEAD
            val process = Runtime.getRuntime().exec("su")
            val os = DataOutputStream(process.outputStream)
            os.writeBytes("$command\n")
            os.writeBytes("exit\n")
            os.flush()
            process.waitFor()
=======
            Runtime.getRuntime()
                .exec(arrayOf("su", "-c", command))
                .waitFor()
>>>>>>> 1044f2a (v3.0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
