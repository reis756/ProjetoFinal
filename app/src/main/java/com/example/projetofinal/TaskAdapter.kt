package com.example.projetofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val dataSet: List<Task>) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_task, viewGroup, false)

        return TaskViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }
}

class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(task: Task) {
        view.findViewById<TextView>(R.id.title).text = task.title
        view.findViewById<TextView>(R.id.description).text = task.description
    }
}