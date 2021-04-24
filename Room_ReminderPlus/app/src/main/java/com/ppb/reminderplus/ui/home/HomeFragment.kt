package com.ppb.reminderplus.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppb.reminderplus.*
import com.ppb.reminderplus.data.AssigmentData
import com.ppb.reminderplus.type.Assigment
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rvAssigment: RecyclerView
    private val list = ArrayList<Assigment>()

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

        list.addAll(AssigmentData.listData)
        showRecyclerCard()
        return root
    }

    private fun showRecyclerCard() {
//        rvAssigment.layoutManager = LinearLayoutManager(context)
//        rvAssigment.adapter = cardAssigmentAd
////        val cardAssigmentAdapter = CardAssigmenapter
        val adapter = ListAssigmentAdapter()
        rvAssigment.adapter = adapter
        rvAssigment.layoutManager = LinearLayoutManager(context)

        assigmentViewModel.allAssigments.observe(viewLifecycleOwner) { result ->
            // Update the cached copy of the words in the adapter.
            result.let { adapter.submitList(it) }
        }

//        assigmentViewModel.all

       adapter.setOnItemClickCallback(object :
            ListAssigmentAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Assigment) {
//
//            }

           override fun onItemClicked(data: com.ppb.reminderplus.database.Assigment) {
               showSelectedAssigment(data)
           }
       })
    }

    private fun showSelectedAssigment(assigment: com.ppb.reminderplus.database.Assigment){
            val moveWithDataIntent = Intent(activity, DetailAssigment::class.java)

//            val extras = Bundle()
//            extras.putString("title", assigment.title)
//            extras.putString("description", assigment.description)
//            extras.putString("date", assigment.date)
//            extras.putInt("photo", assigment.course.photo)
//            extras.putString("course", assigment.course.name)
//            extras.putInt("notify", assigment.notify)
//            extras.putInt("priority", assigment.priority.value)

//            moveWithDataIntent.putExtras(extras)
            startActivity(moveWithDataIntent)
    }
}