package com.edgetoedge.fullscreen

import android.content.Intent
<<<<<<< HEAD
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.edgetoedge.fullscreen.databinding.ActivityMainBinding
import rikka.shizuku.Shizuku
=======
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.edgetoedge.fullscreen.databinding.ActivityMainBinding
>>>>>>> 1044f2a (v3.0)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AppAdapter { packageName ->
            ImmersiveManager.applyImmersive(this, packageName)
<<<<<<< HEAD
=======
            Toast.makeText(this, "Immersive aplicado", Toast.LENGTH_SHORT).show()
>>>>>>> 1044f2a (v3.0)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

<<<<<<< HEAD
=======
        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

>>>>>>> 1044f2a (v3.0)
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
