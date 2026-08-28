package com.example.tp_03_ex2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AlunoNomeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOME = "extra_nome"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aluno_nome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNome = findViewById<TextInputEditText>(R.id.etNome)
        val btnContinuar = findViewById<MaterialButton>(R.id.btnContinuar)
        val btnVoltar = findViewById<MaterialButton>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }

        btnContinuar.setOnClickListener {
            val nome = etNome.text?.toString()?.trim().orEmpty()

            if (nome.isEmpty()) {
                Toast.makeText(this, R.string.error_nome_vazio, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, AlunoMatriculaActivity::class.java).apply {
                putExtra(EXTRA_NOME, nome)
            }
            startActivity(intent)
        }
    }
}
