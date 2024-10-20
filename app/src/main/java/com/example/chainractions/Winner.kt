package com.example.chainractions

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Winner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_winner)

        // Apply window insets for full screen experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the intent extras passed from the GameActivity
        val winner = intent.getStringExtra("who") ?: "Unknown"
        val turns = intent.getIntExtra("turn", 0)
        val greenScore = intent.getIntExtra("greenScore", 0)
        val redScore = intent.getIntExtra("redScore", 0)

        // Set the values in the UI
        findViewById<TextView>(R.id.winnerText).text = "Winner: $winner"
        findViewById<TextView>(R.id.turnText).text = "Turns: $turns"
        findViewById<TextView>(R.id.greenScoreText).text = "Green Score: $greenScore"
        findViewById<TextView>(R.id.redScoreText).text = "Red Score: $redScore"
    }
}