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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.ppb.reminderplus.AssigmentApplication
import com.ppb.reminderplus.AssigmentViewModel
import com.ppb.reminderplus.AssigmentViewModelFactory
import com.ppb.reminderplus.R
import com.ppb.reminderplus.database.Course


class NewCourseFragment : Fragment() {
    val REQUEST_CODE = 100
    var paths: Uri? = null
    var bitmap: Bitmap? = null
    lateinit var image: ImageView
    private val assigmentViewModel: AssigmentViewModel by viewModels {
        AssigmentViewModelFactory((activity?.application as AssigmentApplication).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_new_class, container, false)
        val buttonImage: Button = root.findViewById(R.id.buttonLoadImage)
        val buttonCreate: Button = root.findViewById(R.id.buttonCreate_Class)
        val courseName: EditText = root.findViewById(R.id.newTitleCourse)

        image= root.findViewById(R.id.foto)

        buttonImage.setOnClickListener(View.OnClickListener {
            openGalleryForImage()
        })

        buttonCreate.setOnClickListener(View.OnClickListener {
            var course = Course(0, courseName.text.toString(), bitmap)
            assigmentViewModel.insertCourse(course)
        })

        return root
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
//            imageView.setImageURI(data?.data) // handle chosen image
            paths = data?.data!!
            Log.d("MyTag", data.data.toString())
            if (paths != null) {

                if(Build.VERSION.SDK_INT < 28) {
                    bitmap = MediaStore.Images.Media.getBitmap(
                        activity?.contentResolver,
                        paths
                    )
                } else {
                    val source = ImageDecoder.createSource(activity?.contentResolver!!, paths!!)
                    bitmap = ImageDecoder.decodeBitmap(source)
                }
                Log.d("MyTag", bitmap.toString())
                image.setImageBitmap(bitmap)
            }
        }

    }



}