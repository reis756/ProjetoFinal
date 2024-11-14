package com.example.projetofinal.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projetofinal.R
import com.example.projetofinal.model.User

class UserAdapter(
    private val dataSet: MutableList<User>,
    val listener: Listener
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_user, viewGroup, false)

        return UserViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener {
            listener.onClick(dataSet[position])
        }

        viewHolder.itemView.setOnLongClickListener {
            listener.onLongClick(dataSet[position])
            true
        }

        viewHolder.bind(dataSet[position])
    }

    fun addUsers(users: List<User>) {
        val lastIndex = dataSet.lastIndex
        notifyItemRangeRemoved(0, dataSet.size)

        dataSet.clear()
        dataSet.addAll(users)

        notifyItemRangeInserted(lastIndex, dataSet.size)
    }

    interface Listener {
        fun onClick(user: User)
        fun onLongClick(user: User)
    }
}

class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(task: User) {
        view.findViewById<TextView>(R.id.name).text =
            String.format(view.context.getString(R.string.name), task.name)
        view.findViewById<TextView>(R.id.age).text =
            String.format(view.context.getString(R.string.age), task.age.toString())
    }
}