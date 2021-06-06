package com.ppb.reminderplus.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppb.reminderplus.*
import com.ppb.reminderplus.database.Assigment
import com.ppb.reminderplus.database.AssigmentAndCourse
import com.ppb.reminderplus.database.Course
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rvAssigment: RecyclerView
//    private val list = ArrayList<Assigment>()

    private val assigmentViewModel: AssigmentViewModel by viewModels {
        AssigmentViewModelFactory((activity?.application as AssigmentApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        rvAssigment = root.findViewById(R.id.rv_assigment)
        rvAssigment.setHasFixedSize(true)

//        list.addAll(AssigmentData.listData)
        showRecyclerCard()
        return root
    }

    private fun showRecyclerCard() {
//        rvAssigment.layoutManager = LinearLayoutManager(context)
//        rvAssigment.adapter = cardAssigmentAd
////        val cardAssigmentAdapter = CardAssigmenapter
        val adapter = ParentAssigmentAdapter()
        rvAssigment.adapter = adapter
        rvAssigment.layoutManager = LinearLayoutManager(context)

        assigmentViewModel.findAssigmentCourse.observe(viewLifecycleOwner) { result ->
            // Update the cached copy of the words in the adapter.
            result.let {
                adapter.submitList(it)
            }
        }


//        val icon = BitmapFactory.decodeResource(resources, R.drawable.mobila_programming)
//        var course = Course(2, "PPB", icon)
//        var assigment = Assigment(0, "HOME", "test2", "LOW", Calendar.getInstance(), "sda", "dsds",2)
//        assigmentViewModel.insertAssigment(assigment)
//        assigmentViewModel.insertCourse(course)

//        assigmentViewModel.all

       adapter.setOnItemClickCallback(object :
            ParentAssigmentAdapter.OnItemClickCallback {
           override fun onItemClicked(data: AssigmentAndCourse) {
               showSelectedAssigment(data)
           }
       })
    }


    private fun showSelectedAssigment(assigmentAndCourse: AssigmentAndCourse){
            val moveWithDataIntent = Intent(activity, DetailAssigment::class.java)
            val assigment = assigmentAndCourse.assigments
            val course = assigmentAndCourse.course

            val timeR = (assigment.taskDate.timeInMillis - Calendar.getInstance().timeInMillis).toLong()

            val extras = Bundle()
            extras.putString("title", assigment.title)
            extras.putString("description", assigment.description)
            extras.putInt("idAssigment",assigment.assigment_id)
            extras.putString("date", SimpleDateFormat("yyyy-MM-dd").format(assigment.taskDate.getTime()))
            extras.putString("course", course.name)
            extras.putString("notify", assigment.taskNotify)
            extras.putString("priority", assigment.taskPriority)
            extras.putLong("timeremaining", timeR)
            extras.putString("status",assigment.statusAssigment)
            extras.putByteArray("photo",fromBitmap(course.photo))

            moveWithDataIntent.putExtras(extras)
            startActivity(moveWithDataIntent)
    }

    fun fromBitmap(bitmap: Bitmap?): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }
}