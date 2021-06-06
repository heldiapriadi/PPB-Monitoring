package com.ppb.reminderplus.ui.Insert

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ppb.reminderplus.R
import com.ppb.reminderplus.ui.Insert.newitem.NewAssigment
import com.ppb.reminderplus.ui.Insert.newitem.NewClassFragment
import com.ppb.reminderplus.ui.Insert.newitem.NewCourseFragment

class InsertFragment : Fragment() {

    private lateinit var slideshowViewModel: InsertViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        slideshowViewModel =
                ViewModelProvider(this).get(InsertViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_insert, container, false)

        var fr = activity?.supportFragmentManager?.beginTransaction()
        fr?.replace(R.id.insertItem, NewAssigment())?.addToBackStack(null)
        fr?.commit()
        return root
    }

   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.insert, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_new_assigment -> {
                var fr = activity?.supportFragmentManager?.beginTransaction()
                fr?.replace(R.id.insertItem, NewAssigment())?.addToBackStack(null)
                fr?.commit()
                return true
            }
            R.id.action_new_course -> {
                var fr = activity?.supportFragmentManager?.beginTransaction()
                fr?.replace(R.id.insertItem, NewCourseFragment())?.addToBackStack(null)
                fr?.commit()
                return true
            }
            R.id.action_new_class -> {
                var fr = activity?.supportFragmentManager?.beginTransaction()
                fr?.replace(R.id.insertItem, NewClassFragment())?.addToBackStack(null)
                fr?.commit()
                return true
            }


            else -> {
            }
        }
        return false
    }



}

