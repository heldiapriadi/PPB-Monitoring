package com.ppb.reminderplus

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ppb.reminderplus.database.Assigment
import com.ppb.reminderplus.ListAssigmentAdapter.AssigmentViewHolder
import com.ppb.reminderplus.type.Priority

class ListAssigmentAdapter : ListAdapter<Assigment, AssigmentViewHolder>(AssigmentsComparator()) {
    private lateinit var onItemClickCallback: ListAssigmentAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: ListAssigmentAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssigmentViewHolder {
        return AssigmentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AssigmentViewHolder, position: Int) {
        val current = getItem(position)
        holder.tvTitle.text = current.title
        holder.imgPhoto.setBackgroundColor(getPriorityColor(current.taskPriority))
        holder.tvDescription.text = current.description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(getItem(holder.adapterPosition))}
    }

    class AssigmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

        companion object {
            fun create(parent: ViewGroup): AssigmentViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_list_assigment, parent, false)
                return AssigmentViewHolder(view)
            }
        }
    }

    class AssigmentsComparator : DiffUtil.ItemCallback<Assigment>() {
        override fun areItemsTheSame(oldItem: Assigment, newItem: Assigment): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Assigment, newItem: Assigment): Boolean {
            return oldItem.assigment_id == newItem.assigment_id
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Assigment)
    }

    fun getPriorityColor(value: String):Int{
        if (value == "HIGH"){
            return Color.RED
        }else if (value == "NORMAL"){
            return Color.YELLOW
        }else
        {
            return Color.GREEN
        }
    }
}