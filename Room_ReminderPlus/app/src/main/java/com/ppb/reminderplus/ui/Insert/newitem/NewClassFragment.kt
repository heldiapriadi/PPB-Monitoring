package com.ppb.reminderplus.ui.Insert.newitem

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ppb.reminderplus.AssigmentApplication
import com.ppb.reminderplus.AssigmentViewModel
import com.ppb.reminderplus.AssigmentViewModelFactory
import com.ppb.reminderplus.R
import com.ppb.reminderplus.database.Course


class NewClassFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_new_event, container, false)
        return root
    }

}