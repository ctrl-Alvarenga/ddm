package com.example.tp_03_ex2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

class AlunoMatriculaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aluno_matricula)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nome = intent.getStringExtra(AlunoNomeActivity.EXTRA_NOME).orEmpty()

        val tvNomeValue = findViewById<TextView>(R.id.tvNomeValue)
        val tvMatriculaValue = findViewById<TextView>(R.id.tvMatriculaValue)
        val btnGerarMatricula = findViewById<MaterialButton>(R.id.btnGerarMatricula)
        val btnVoltar = findViewById<MaterialButton>(R.id.btnVoltar)

        tvNomeValue.text = nome

        btnVoltar.setOnClickListener {
            finish()
        }

        btnGerarMatricula.setOnClickListener {
            val matricula = Random.nextInt(100000, 999999)
            AlunoDao.salvar(Aluno(nome, matricula))
            tvMatriculaValue.text = matricula.toString()
        }
    }
}
