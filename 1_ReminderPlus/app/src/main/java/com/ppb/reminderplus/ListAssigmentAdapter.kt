package com.ppb.reminderplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListAssigmentAdapter(private val listAssigment: ArrayList<Assigment>) :
    RecyclerView.Adapter<ListAssigmentAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_assigment, viewGroup, false)
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
        holder.tvTitle.text = assigment.title
        holder.tvDescription.text = assigment.description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAssigment[holder.adapterPosition])}
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Assigment)
    }

}