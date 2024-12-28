package com.example.fileexplorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import com.example.fileexplorer.R

class FileAdapter(
    private val files: List<File>,
    private val onFileClick: (File) -> Unit
) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.file_name)
        val iconImage: ImageView = view.findViewById(R.id.file_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_file, parent, false)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        holder.nameText.text = file.name
        holder.itemView.setOnClickListener { onFileClick(file) }
        
        holder.iconImage.setImageResource(
            if (file.isDirectory) android.R.drawable.ic_menu_more
            else android.R.drawable.ic_menu_edit
        )
    }

    override fun getItemCount() = files.size
} 