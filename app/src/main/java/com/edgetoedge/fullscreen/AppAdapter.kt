package com.edgetoedge.fullscreen

import android.content.pm.ResolveInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edgetoedge.fullscreen.databinding.ItemAppBinding

class AppAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<AppAdapter.ViewHolder>() {

    private var apps: List<ResolveInfo> = emptyList()

    fun submitList(list: List<ResolveInfo>) {
        apps = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemAppBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAppBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = apps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = apps[position]
        holder.binding.appName.text =
            app.loadLabel(holder.itemView.context.packageManager)

        holder.binding.root.setOnClickListener {
            onClick(app.activityInfo.packageName)
        }
    }
}
