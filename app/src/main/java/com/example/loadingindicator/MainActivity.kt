package com.example.loadingindicator

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var statusTextView: TextView
    private lateinit var startButton: Button
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        statusTextView = findViewById(R.id.statusTextView)
        startButton = findViewById(R.id.startButton)

        startButton.setOnClickListener {
            simulateLoading()
        }
    }

    private fun simulateLoading() {
        progressBar.progress = 0
        progressBar.setBackgroundColor(Color.GRAY)
        statusTextView.text = "Загрузка началась..."
        statusTextView.setTextColor(Color.GRAY)

        handler.postDelayed(object : Runnable {
            var progress = 0

            override fun run() {
                progress += 10
                progressBar.progress = progress

                when {
                    progress < 50 -> {
                        statusTextView.text = "Идёт загрузка..."
                        statusTextView.setTextColor(Color.BLUE)
                    }
                    progress < 100 -> {
                        statusTextView.text = "Загрузка почти завершена..."
                        statusTextView.setTextColor(Color.MAGENTA)
                    }
                    else -> {
                        statusTextView.text = "Загрузка завершена!"
                        statusTextView.setTextColor(Color.GREEN)
                        return
                    }
                }

                handler.postDelayed(this, 500)
            }
        }, 500)
    }
}
