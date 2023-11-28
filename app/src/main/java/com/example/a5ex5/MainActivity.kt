package com.example.a5ex5

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var resultado = 0
    private var score = 0
    private var minhaTAG = "ParouImpar"
    lateinit var txtResultado : TextView
    lateinit var btnNovo: Button
    private lateinit var preferences: SharedPreferences

    private fun novoJogo(){
        txtResultado.text = "Par ou Impar ?"
        resultado = Random.nextInt(0,10)
        btnNovo.visibility = View.INVISIBLE
    }

    fun novoJogo(view: View){
        novoJogo()
    }
    fun jogada(view: View) {
        if (resultado % 2 == view.tag.toString().toInt()) {
            if (btnNovo.visibility == View.INVISIBLE)
                score++
            salvarScore()
        }

        title = "Score do jogo: $score"
        txtResultado.text = "$resultado"

        btnNovo.visibility = View.VISIBLE
    }

    private fun salvarScore() {
        preferences.edit().putInt("SCORE", score).apply()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(minhaTAG, "app iniciado")

        txtResultado = findViewById(R.id.txtResultado)
        btnNovo = findViewById(R.id.btnNovo)

        preferences = getPreferences(MODE_PRIVATE)
        score = preferences.getInt("SCORE", 0)
        title = "Score do jogo: $score"

        novoJogo()
    }
}