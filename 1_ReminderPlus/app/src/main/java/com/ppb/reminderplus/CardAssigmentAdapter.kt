package com.ppb.reminderplus

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.ppb.reminderplus.type.Assigment
import com.ppb.reminderplus.type.Priority


class CardAssigmentAdapter(private val listAssigment: ArrayList<Assigment>) :
    RecyclerView.Adapter<CardAssigmentAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_overview_card, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAssigment.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val assigment = listAssigment[position]
        Glide.with(holder.itemView.context)
            .load(assigment.course.photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvPriority.setBackgroundColor(getPriorityColor(assigment.priority))
        holder.tvTitle.text = assigment.title
        holder.tvDescription.text = assigment.description
        holder.tvDate.text = assigment.date
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAssigment[holder.adapterPosition])}
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvDate: TextView = itemView.findViewById(R.id.tv_item_date)
        var tvPriority: ShapeableImageView = itemView.findViewById(R.id.priority)
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Assigment)
    }

    fun getPriorityColor(value: Priority):Int{
        if (value == Priority.HIGH){
            return Color.RED
        }else if (value ==Priority.NORMAL){
            return Color.YELLOW
        }else
        {
            return Color.GREEN
        }
    }

}