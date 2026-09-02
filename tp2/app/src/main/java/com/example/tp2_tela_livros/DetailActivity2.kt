package com.example.tp2_tela_livros
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val textTitulo = findViewById<TextView>(R.id.textTituloDetalhe)
        val textAutor = findViewById<TextView>(R.id.textAutorDetalhe)

        val titulo = intent.getStringExtra("EXTRA_TITULO") ?: ""
        val autor = intent.getStringExtra("EXTRA_AUTOR") ?: ""

        textTitulo.text = titulo
        textAutor.text = autor
    }
}