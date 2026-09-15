package com.example.meusfilmesfavoritos

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private lateinit var fabAdd: FloatingActionButton

    private val movieList = mutableListOf(
        Movie("Interestelar", "Christopher Nolan"),
        Movie("Cidade de Deus", "Fernando Meirelles"),
        Movie("O Poderoso Chefão", "Francis Ford Coppola"),
        Movie("A Odisseia", "Christopher Nolan"),
        Movie("O Grande Mestre", "Wong Kar-Wai"),
        Movie("Clube da Luta", "David Fincher")
    )

    private var isGridMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewMovies)
        fabAdd = findViewById(R.id.fabAddMovie)

        adapter = MovieAdapter(movieList)
        recyclerView.adapter = adapter

        // Por padrão exibimos em Grid (2 colunas), conforme solicitado
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        fabAdd.setOnClickListener {
            showAddMovieDialog()
        }
    }

    // Cria o menu de opções (usado para alternar entre Grid e Lista)
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_toggle_layout -> {
                toggleLayoutManager(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleLayoutManager(item: android.view.MenuItem) {
        isGridMode = !isGridMode
        if (isGridMode) {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            item.title = getString(R.string.action_view_as_list)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(this)
            item.title = getString(R.string.action_view_as_grid)
        }
    }

    private fun showAddMovieDialog() {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_add_movie, null)

        val etTitle = dialogView.findViewById<EditText>(R.id.etMovieTitle)
        val etDirector = dialogView.findViewById<EditText>(R.id.etMovieDirector)

        AlertDialog.Builder(this)
            .setTitle("Adicionar filme")
            .setView(dialogView)
            .setPositiveButton("Salvar") { _, _ ->
                val title = etTitle.text.toString().trim()
                val director = etDirector.text.toString().trim()

                if (title.isEmpty() || director.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Preencha título e diretor",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setPositiveButton
                }

                adapter.addMovie(Movie(title, director))
                recyclerView.scrollToPosition(adapter.itemCount - 1)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
