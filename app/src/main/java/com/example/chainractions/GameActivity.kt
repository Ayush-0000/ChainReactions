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
    private val number = 6
    private val a = Array(number) { arrayOfNulls<Button>(number) }
    private val aa = Array(number) { IntArray(number) }
    private val bb = Array(number) { CharArray(number) }
    private var turn = 0

    private lateinit var rs: Resources
    private var dcol: Int = 0
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

        a[0][0] = findViewById(R.id.a1)
        a[0][1] = findViewById(R.id.a2)
        a[0][2] = findViewById(R.id.a3)
        a[0][3] = findViewById(R.id.a4)
        a[0][4] = findViewById(R.id.a5)
        a[0][5] = findViewById(R.id.a6)

        a[1][0] = findViewById(R.id.b1)
        a[1][1] = findViewById(R.id.b2)
        a[1][2] = findViewById(R.id.b3)
        a[1][3] = findViewById(R.id.b4)
        a[1][4] = findViewById(R.id.b5)
        a[1][5] = findViewById(R.id.b6)

        a[2][0] = findViewById(R.id.c1)
        a[2][1] = findViewById(R.id.c2)
        a[2][2] = findViewById(R.id.c3)
        a[2][3] = findViewById(R.id.c4)
        a[2][4] = findViewById(R.id.c5)
        a[2][5] = findViewById(R.id.c6)

        a[3][0] = findViewById(R.id.d1)
        a[3][1] = findViewById(R.id.d2)
        a[3][2] = findViewById(R.id.d3)
        a[3][3] = findViewById(R.id.d4)
        a[3][4] = findViewById(R.id.d5)
        a[3][5] = findViewById(R.id.d6)

        a[4][0] = findViewById(R.id.e1)
        a[4][1] = findViewById(R.id.e2)
        a[4][2] = findViewById(R.id.e3)
        a[4][3] = findViewById(R.id.e4)
        a[4][4] = findViewById(R.id.e5)
        a[4][5] = findViewById(R.id.e6)

        a[5][0] = findViewById(R.id.f1)
        a[5][1] = findViewById(R.id.f2)
        a[5][2] = findViewById(R.id.f3)
        a[5][3] = findViewById(R.id.f4)
        a[5][4] = findViewById(R.id.f5)
        a[5][5] = findViewById(R.id.f6)

        a[0][0]?.setOnClickListener { /* Handle click */ }
        for (y in 0 until number) {
            for (z in 0 until number) {
                a[y][z]?.setOnClickListener { /* Handle click */ }
                aa[y][z] = 0
                bb[y][z] = 'n'
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val mf = menuInflater
        mf.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val s = item.title.toString()
        display(s)
        return super.onOptionsItemSelected(item)
    }

    private var fsource: Char = 'g'

    override fun onClick(view: View) {
        val b = view as Button

        for (i in 0 until number) {
            for (k in 0 until number) {
                if (b == a[i][k]) {
                    try {
                        turn++
                        chal(i, k, fsource, false)
                        isWon()
                        fsource = if (fsource == 'g') 'r' else 'g'
                    } catch (e: Exception) {
                        display(e.toString())
                    }
                }
            }
        }
    }

// a=button
// aa=number
// bb=char
// pos= 3(Center), 2(Edge), 1(Corner)

    @Throws(Exception::class)
    private fun chal(i: Int, j: Int, source: Char, isRec: Boolean) {
        var pos = 3
        if ((i == 0 && j == 0) || (i == number - 1 && j == number - 1) || (i == 0 && j == number - 1) || (i == number - 1 && j == 0)) {
            pos = 1
        } else if (i == 0 || i == number - 1 || j == 0 || j == number - 1) {
            pos = 2
        }

        if (!isRec) {
            if (bb[i][j] == 'n') {
                aa[i][j] = 1
                bb[i][j] = source
                a[i][j].text = aa[i][j].toString()
                val bcol = if (source == 'g') Color.GREEN else Color.RED
                a[i][j].setBackgroundColor(bcol)
            } else {
                if (bb[i][j] != source) {
                    display("Invalid move")
                    fsource = if (fsource == 'g') 'r' else 'g'
                    turn--
                } else {
                    if (aa[i][j] < pos) {
                        aa[i][j] += 1
                        a[i][j].text = aa[i][j].toString()
                    } else {
                        aa[i][j] = 0
                        bb[i][j] = 'n'
                        a[i][j].text = ""
                        a[i][j].setBackgroundColor(dcol)
                        try {
                            chal(i - 1, j, source, true)
                        } catch (e: Exception) {}
                        try {
                            chal(i + 1, j, source, true)
                        } catch (e: Exception) {}
                        try {
                            chal(i, j - 1, source, true)
                        } catch (e: Exception) {}
                        try {
                            chal(i, j + 1, source, true)
                        } catch (e: Exception) {}
                    }
                }
            }
        } else { // isRec == true
            if (bb[i][j] == 'n') {
                aa[i][j] = 1
                bb[i][j] = source
                a[i][j].text = aa[i][j].toString()
                val bcol = if (source == 'g') Color.GREEN else Color.RED
                a[i][j].setBackgroundColor(bcol)
            } else {
                bb[i][j] = source
                if (aa[i][j] < pos) {
                    aa[i][j] += 1
                    a[i][j].text = aa[i][j].toString()
                    val bcol = if (source == 'g') Color.GREEN else Color.RED
                    a[i][j].setBackgroundColor(bcol)
                } else {
                    aa[i][j] = 0
                    bb[i][j] = 'n'
                    a[i][j].text = ""
                    a[i][j].setBackgroundColor(dcol)
                    try {
                        chal(i - 1, j, source, true)
                    } catch (e: Exception) {}
                    try {
                        chal(i + 1, j, source, true)
                    } catch (e: Exception) {}
                    try {
                        chal(i, j - 1, source, true)
                    } catch (e: Exception) {}
                    try {
                        chal(i, j + 1, source, true)
                    } catch (e: Exception) {}
                }
            }
        }
    } // chal END

    private fun isWon() {
        var gWon = true
        var rWon = true
        if (turn > 2) {
            for (i in 0 until number) {
                for (k in 0 until number) {
                    when (bb[i][k]) {
                        'r' -> gWon = false
                        'g' -> rWon = false
                    }
                }
            }
            if (gWon) {
                display("Green Won")
                val intent = Intent(this, Winner::class.java).apply {
                    putExtra("which", "easy")
                    putExtra("who", "green")
                    putExtra("turn", turn)
                }
                startActivity(intent)
            } else if (rWon) {
                display("Red Won")
                val intent = Intent(this, Winner::class.java).apply {
                    putExtra("which", "easy")
                    putExtra("who", "red")
                    putExtra("turn", turn)
                }
                startActivity(intent)
            }
        }
    } // isWon END

    private fun display(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }




}