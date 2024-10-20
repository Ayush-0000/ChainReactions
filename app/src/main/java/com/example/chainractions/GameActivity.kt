package com.example.chainractions

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameActivity : AppCompatActivity() {
    private val number = 5
    private val a = Array(number) { arrayOfNulls<Button>(number) }
    private val aa = Array(number) { IntArray(number) }
    private val bb = Array(number) { CharArray(number) }
    private var turn = 0
    private lateinit var rs: Resources
    private var dcol: Int = 0
    private var fsource: Char = 'g'
    private var greenScore = 0
    private var redScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rs = this.resources
        dcol = rs.getColor(R.color.blockBack)

        initializeGrid()
        setupGame()
    }

    private fun initializeGrid() {
        val buttonIds = listOf(
            R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5,
            R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5,
            R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5,
            R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5,
            R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5
        )

        for (i in 0 until number) {
            for (j in 0 until number) {
                a[i][j] = findViewById(buttonIds[i * number + j])
                a[i][j]?.setOnClickListener { onClick(it) }
                aa[i][j] = 0
                bb[i][j] = 'n'
            }
        }
    }

    private fun setupGame() {
        turn = 0
        fsource = 'g'
        greenScore = 0
        redScore = 0
        for (i in 0 until number) {
            for (j in 0 until number) {
                aa[i][j] = 0
                bb[i][j] = 'n'
                a[i][j]?.text = ""
                a[i][j]?.setBackgroundColor(dcol)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val s = item.title.toString()
        display(s)
        return super.onOptionsItemSelected(item)
    }

    fun onClick(view: View) {
        val b = view as Button
        for (i in 0 until number) {
            for (k in 0 until number) {
                if (b == a[i][k]) {
                    try {
                        turn++
                        chal(i, k, fsource, false)
                        updateScores()
                        checkVictoryConditions()
                        fsource = if (fsource == 'g') 'r' else 'g'
                    } catch (e: Exception) {
                        display(e.toString())
                    }
                }
            }
        }
    }

    @Throws(Exception::class)
    private fun chal(i: Int, j: Int, source: Char, isRec: Boolean) {
        val pos = getMaxValue(i, j)

        if (!isRec) {
            when {
                bb[i][j] == 'n' -> {
                    aa[i][j] = 1
                    bb[i][j] = source
                    updateSquareUI(i, j)
                }
                bb[i][j] != source -> {
                    display("Invalid move")
                    fsource = if (fsource == 'g') 'r' else 'g'
                    turn--
                }
                aa[i][j] < pos -> {
                    aa[i][j]++
                    updateSquareUI(i, j)
                }
                else -> collapse(i, j, source)
            }
        } else {
            bb[i][j] = source
            when {
                aa[i][j] < pos -> {
                    aa[i][j]++
                    updateSquareUI(i, j)
                }
                else -> collapse(i, j, source)
            }
        }
    }

    private fun collapse(i: Int, j: Int, source: Char) {
        aa[i][j] = 0
        bb[i][j] = 'n'
        updateSquareUI(i, j)

        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        for ((dx, dy) in directions) {
            try {
                chal(i + dx, j + dy, source, true)
            } catch (e: Exception) {}
        }
    }

    private fun updateSquareUI(i: Int, j: Int) {
        a[i][j]?.text = if (aa[i][j] > 0) aa[i][j].toString() else ""
        val bcol = when (bb[i][j]) {
            'g' -> Color.GREEN
            'r' -> Color.RED
            else -> dcol
        }
        a[i][j]?.setBackgroundColor(bcol)
    }

    private fun updateScores() {
        greenScore = 0
        redScore = 0
        for (i in 0 until number) {
            for (j in 0 until number) {
                when (bb[i][j]) {
                    'g' -> greenScore++
                    'r' -> redScore++
                }
            }
        }
    }

    private fun checkVictoryConditions() {
        when {
            greenScore >= 10 -> endGame("Green")
            redScore >= 10 -> endGame("Red")
            isGameOver() -> {
                when {
                    greenScore > redScore -> endGame("Green")
                    redScore > greenScore -> endGame("Red")
                    else -> endGame("Draw")
                }
            }
        }
    }

    private fun isGameOver(): Boolean {
        for (i in 0 until number) {
            for (j in 0 until number) {
                if (bb[i][j] == 'n' || aa[i][j] < getMaxValue(i, j)) {
                    return false
                }
            }
        }
        return true
    }

    private fun getMaxValue(i: Int, j: Int): Int {
        return when {
            (i == 0 && j == 0) || (i == number - 1 && j == number - 1) ||
                    (i == 0 && j == number - 1) || (i == number - 1 && j == 0) -> 1
            i == 0 || i == number - 1 || j == 0 || j == number - 1 -> 2
            else -> 3
        }
    }

    private fun endGame(winner: String) {
        val message = when (winner) {
            "Green" -> "Green Won"
            "Red" -> "Red Won"
            else -> "It's a Draw"
        }
        display(message)
        startWinnerActivity(winner.toLowerCase())
    }

    private fun startWinnerActivity(winner: String) {
        val intent = Intent(this, Winner::class.java).apply {
            putExtra("which", "easy")
            putExtra("who", winner)
            putExtra("turn", turn)
            putExtra("greenScore", greenScore)
            putExtra("redScore", redScore)
        }
        startActivity(intent)
        finish() // End this activity to prevent going back to the game
    }

    private fun display(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
