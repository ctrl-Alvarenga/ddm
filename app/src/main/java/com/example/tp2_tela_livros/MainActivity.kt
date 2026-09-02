package com.example.tp2_tela_livros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTitulo = findViewById<EditText>(R.id.editTitulo)
        val editAutor = findViewById<EditText>(R.id.editAutor)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val titulo = editTitulo.text.toString().trim()
            val autor = editAutor.text.toString().trim()

            if (titulo.isEmpty() || autor.isEmpty()) {
                Toast.makeText(this, "Preencha título e autor", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SegundaTelaActivity2::class.java)
                intent.putExtra("EXTRA_TITULO", titulo)
                intent.putExtra("EXTRA_AUTOR", autor)
                startActivity(intent)
            }
        }
    }
}