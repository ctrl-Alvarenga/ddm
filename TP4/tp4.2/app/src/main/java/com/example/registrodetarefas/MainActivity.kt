package com.example.registrodetarefas

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private lateinit var fabAdd: FloatingActionButton

    private val taskList = mutableListOf(
        Task("Estudar Kotlin", "Revisar RecyclerView e ViewHolders"),
        Task("Fazer compras", "Comprar itens da lista de mercado"),
        Task("Enviar relatório", "Finalizar e enviar o relatório mensal")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewTasks)
        fabAdd = findViewById(R.id.fabAddTask)

        adapter = TaskAdapter(taskList)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        fabAdd.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun showAddTaskDialog() {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_add_task, null)

        val etName = dialogView.findViewById<EditText>(R.id.etTaskName)
        val etDescription = dialogView.findViewById<EditText>(R.id.etTaskDescription)

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_add_task_title))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.action_save)) { _, _ ->
                val name = etName.text.toString().trim()
                val description = etDescription.text.toString().trim()

                if (name.isEmpty() || description.isEmpty()) {
                    Toast.makeText(
                        this,
                        getString(R.string.error_empty_fields),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setPositiveButton
                }

                adapter.addTask(Task(name, description))
                recyclerView.scrollToPosition(adapter.itemCount - 1)
            }
            .setNegativeButton(getString(R.string.action_cancel), null)
            .show()
    }
}
