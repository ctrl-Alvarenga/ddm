package com.example.registrodetarefas

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(
    private val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.cardTask)
        val tvName: TextView = itemView.findViewById(R.id.tvTaskName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)
        val btnToggleDone: Button = itemView.findViewById(R.id.btnToggleDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        val context = holder.itemView.context

        holder.tvName.text = task.name
        holder.tvDescription.text = task.description

        applyDoneStyle(holder, task.isDone)

        holder.btnToggleDone.setOnClickListener {
            task.isDone = !task.isDone
            applyDoneStyle(holder, task.isDone)
        }
    }

    private fun applyDoneStyle(holder: TaskViewHolder, isDone: Boolean) {
        val context = holder.itemView.context

        if (isDone) {
            holder.tvName.paintFlags = holder.tvName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.tvDescription.paintFlags =
                holder.tvDescription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.card.alpha = 0.6f
            holder.btnToggleDone.text = context.getString(R.string.btn_mark_pending)
        } else {
            holder.tvName.paintFlags = holder.tvName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.tvDescription.paintFlags =
                holder.tvDescription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.card.alpha = 1.0f
            holder.btnToggleDone.text = context.getString(R.string.btn_mark_done)
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }
}
