package com.example.fileexplorer

import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import com.example.fileexplorer.FileAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var currentPathText: TextView
    private var currentPath: File = Environment.getExternalStorageDirectory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setupViews()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.files_recycler_view)
        currentPathText = findViewById(R.id.current_path)
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        loadFiles(currentPath)
    }

    private fun loadFiles(directory: File) {
        currentPathText.text = directory.absolutePath
        val files = directory.listFiles()?.sortedBy { !it.isDirectory } ?: emptyList()
        
        recyclerView.adapter = FileAdapter(files) { file ->
            if (file.isDirectory) {
                loadFiles(file)
            }
        }
    }
} 