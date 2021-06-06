package com.ppb.reminderplus.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.ppb.reminderplus.*
import java.util.*
import java.util.concurrent.TimeUnit


class DetailAssigment: AppCompatActivity()  {
    private val assigmentViewModel: AssigmentViewModel by viewModels {
        AssigmentViewModelFactory((application as AssigmentApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_assigment)
        this.setTitle("Detail Assigment")
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.colorPrimary)))

        val gambarCourse: ImageView = findViewById(R.id.img_item_photoo)
        val title: TextView = findViewById(R.id.titleDetail)
        val date: TextView = findViewById(R.id.dateAssigment)
        val desc: TextView = findViewById(R.id.description)
        val course: TextView = findViewById(R.id.course)
        val timeRemaining: TextView = findViewById(R.id.timeRemaining)
        val status: TextView = findViewById(R.id.statusAssigment)
        val buttonDone: Button = findViewById(R.id.buttonDone)

        if( !intent.extras?.isEmpty!!){
            val bundle = intent.extras
            val id = bundle?.getInt("idAssigment")
            val pic = bundle?.getByteArray("photo")
            gambarCourse.setImageBitmap(toBitmap(pic!!))
            title.setText(bundle.getString("title"))
            date.setText(bundle.getString("date"))
            desc.setText(bundle.getString("description"))
            course.setText(bundle.getString("course"))
            val millis: Long =  bundle.getLong("timeremaining")
            val hms = String.format("%02d hours %02d minutes and %02d seconds",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)))
            timeRemaining.setText(hms)

            status.setText(bundle.getString("status"))

            buttonDone.setOnClickListener(View.OnClickListener {
                Log.d("MyTag","Delete" + id.toString())
//                if (id != null) {
                    assigmentViewModel.deleteAssigment(id!!)
//                }
                val moveWithDataIntent = Intent(this@DetailAssigment, MainActivity::class.java)
                startActivity(moveWithDataIntent)
            })
        }

    }

    fun toBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}