package com.edgetoedge.fullscreen

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.edgetoedge.fullscreen.databinding.ActivityMainBinding
import rikka.shizuku.Shizuku

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AppAdapter { packageName ->
            ImmersiveManager.applyImmersive(this, packageName)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        loadApps()
    }

    private fun loadApps() {
        val pm = packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val apps = pm.queryIntentActivities(intent, 0)
        adapter.submitList(apps)
    }
}
