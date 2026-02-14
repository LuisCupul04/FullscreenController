package com.edgetoedge.fullscreen

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edgetoedge.fullscreen.databinding.ActivitySettingsBinding
import rikka.shizuku.Shizuku

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkStatus()

        binding.btnCheckRoot.setOnClickListener {
            if (RootExecutor.isRootAvailable()) {
                Toast.makeText(this, "Root detectado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Root NO detectado", Toast.LENGTH_SHORT).show()
            }
            checkStatus()
        }

        binding.btnCheckShizuku.setOnClickListener {
            requestShizukuPermission()
        }

        binding.btnOpenShizuku.setOnClickListener {
            openShizukuApp()
        }
    }

    private fun requestShizukuPermission() {

        if (!Shizuku.pingBinder()) {
            Toast.makeText(this, "Shizuku no está activo", Toast.LENGTH_SHORT).show()
            return
        }

        if (Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
            Shizuku.requestPermission(100)
            return
        }

        Toast.makeText(this, "Shizuku ACTIVO", Toast.LENGTH_SHORT).show()
        checkStatus()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        checkStatus()
    }

    private fun checkStatus() {

        val shizukuActive = Shizuku.pingBinder()
        val shizukuPermission =
            Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED

        binding.txtRootStatus.text =
            if (RootExecutor.isRootAvailable()) "Root: ACTIVO"
            else "Root: INACTIVO"

        binding.txtShizukuStatus.text =
            if (shizukuActive && shizukuPermission)
                "Shizuku: ACTIVO"
            else
                "Shizuku: INACTIVO"
    }

    private fun openShizukuApp() {
        val intent =
            packageManager.getLaunchIntentForPackage("moe.shizuku.privileged.api")

        if (intent != null) {
            startActivity(intent)
        } else {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=moe.shizuku.privileged.api")
                )
            )
        }
    }
}
