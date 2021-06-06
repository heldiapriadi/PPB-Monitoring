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
import com.ppb.reminderplus.ParentAssigmentAdapter.TestViewHolder
import com.ppb.reminderplus.database.AssigmentAndCourse

class ParentAssigmentAdapter : ListAdapter<AssigmentAndCourse, TestViewHolder>(AssigmentsComparator()) {
    private lateinit var onItemClickCallback: ParentAssigmentAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: ParentAssigmentAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val current = getItem(position)
        val course = current.course
        val assigment = current.assigments
        holder.tvTitle.text = assigment.title

//        holder.imgPhoto.setBackgroundColor(getPriorityColor(current.taskPriority))
        holder.imgPhoto.setImageBitmap(course.photo)
//        holder.tvDescription.text = current.description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(getItem(holder.adapterPosition))}
    }

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

        companion object {
            fun create(parent: ViewGroup): TestViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_assigment, parent, false)
                return TestViewHolder(view)
            }
        }
    }

    class AssigmentsComparator : DiffUtil.ItemCallback<AssigmentAndCourse>() {
        override fun areItemsTheSame(oldItem: AssigmentAndCourse, newItem: AssigmentAndCourse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AssigmentAndCourse, newItem: AssigmentAndCourse): Boolean {
            return oldItem.assigments.equals(newItem.assigments)
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: AssigmentAndCourse)
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