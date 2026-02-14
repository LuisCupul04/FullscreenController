package com.edgetoedge.fullscreen

<<<<<<< HEAD
object ShizukuExecutor {
    fun isAvailable(): Boolean = false
    fun execute(command: String): String? = null
}
=======
import android.content.pm.PackageManager
import rikka.shizuku.Shizuku
import java.lang.reflect.Method

object ShizukuExecutor {

    fun isAvailable(): Boolean {
        return Shizuku.pingBinder() &&
                Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    }

    fun execute(command: String) {

        if (!isAvailable()) return

        try {

            val method: Method = Shizuku::class.java
                .getDeclaredMethod(
                    "newProcess",
                    Array<String>::class.java,
                    Array<String>::class.java,
                    String::class.java
                )

            method.isAccessible = true

            val process = method.invoke(
                null,
                arrayOf("sh", "-c", command),
                null,
                null
            ) as Process

            process.waitFor()
            process.destroy()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
>>>>>>> 1044f2a (v3.0)
