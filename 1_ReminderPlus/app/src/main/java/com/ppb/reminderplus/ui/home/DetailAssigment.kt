package com.ppb.reminderplus.ui.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ppb.reminderplus.R

class DetailAssigment: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_assigment)
        this.setTitle("Detail Assigment")

        val gambarCourse: ImageView = findViewById(R.id.img_item_photoo)
        val title: TextView = findViewById(R.id.titleDetail)
        val date: TextView = findViewById(R.id.dateAssigment)
        val desc: TextView = findViewById(R.id.description)
        val course: TextView = findViewById(R.id.course)

        //---get the Bundle object passed in---
        val bundle = intent.extras
        val pic = bundle!!.getInt("photo")
        gambarCourse.setImageResource(pic)
        title.setText(bundle.getString("title"))
        date.setText(bundle.getString("date"))
        desc.setText(bundle.getString("description"))
        course.setText(bundle.getString("course"))
    }


}