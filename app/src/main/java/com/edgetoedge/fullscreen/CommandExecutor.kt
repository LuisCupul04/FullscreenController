package com.edgetoedge.fullscreen

object CommandExecutor {

    fun execute(command: String) {

        when {
            RootExecutor.isRootAvailable() -> {
                RootExecutor.execute(command)
            }

            ShizukuExecutor.isAvailable() -> {
                ShizukuExecutor.execute(command)
            }

            else -> {
                // No hace nada
            }
        }
    }
}
