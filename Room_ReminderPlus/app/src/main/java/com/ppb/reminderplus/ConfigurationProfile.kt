package com.ppb.reminderplus

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.ppb.reminderplus.type.Profile
import java.io.ByteArrayOutputStream
import java.io.InputStream


class ConfigurationProfile : AppCompatActivity() {
    val REQUEST_CODE = 100
    var paths: Uri? = null
    var bitmap: Bitmap? = null
    var photoProfile: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration_profile)
        title = "Change Profile"

        var actionBar = getSupportActionBar()
        actionBar?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.colorPrimary)))

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        val nameProfile: TextView = findViewById(R.id.confNameProfile)
        val statusProfile: TextView = findViewById(R.id.confStatusProfile)
        photoProfile = findViewById(R.id.fotoprofile)
        val changePhoto: ImageView = findViewById(R.id.confPhoto)
        val confCreate: Button = findViewById(R.id.confCreate)
        val confCancel: Button = findViewById(R.id.confCancel)


        openFileInput("profile").bufferedReader().use { lines ->
            val gson = Gson()
            val profile: Profile = gson.fromJson(lines.readText(), Profile::class.java)
            nameProfile.text = profile.name
            statusProfile.text = profile.status
        }
//        photoProfile?.setImageURI(paths)

        val iss: InputStream = openFileInput("photoProfile")
        bitmap = BitmapFactory.decodeStream(iss)
        photoProfile?.setImageBitmap(bitmap)
        iss.close()

        changePhoto.setOnClickListener(View.OnClickListener {
            openGalleryForImage()
        })

        confCreate.setOnClickListener(View.OnClickListener {
            val profile: Profile = Profile(nameProfile.text.toString(), "photoProfile", statusProfile.text.toString())
            val filename = "profile"
            val gson = Gson()
            val json = gson.toJson(profile)
            val fileContents = json

            openFileOutput(filename, MODE_PRIVATE).use {
                it.write(fileContents.toByteArray())
            }

            openFileOutput("photoProfile", MODE_PRIVATE).use {
                val bos = ByteArrayOutputStream()
                bitmap?.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
                it.write(bos.toByteArray())
            }

            val intent = Intent(this@ConfigurationProfile, MainActivity::class.java)
            startActivity(intent)

        })

        confCancel.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    private fun openGalleryForImage() {
        val intentGallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intentGallery, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if(data != null)
            {
                paths = data.data
                if (paths != null) {
                    if(Build.VERSION.SDK_INT < 28) {
                        bitmap = MediaStore.Images.Media.getBitmap(
                                contentResolver,
                                paths
                        )
                    } else {
                        val source = ImageDecoder.createSource(contentResolver, paths!!)
                        bitmap = ImageDecoder.decodeBitmap(source)
                    }
                    photoProfile?.setImageURI(paths)
                }
            }

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}