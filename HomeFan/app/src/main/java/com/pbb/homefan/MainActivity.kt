package com.pbb.homefan


import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){
    var toggleButton: ToggleButton? = null
    var imageView: ImageView? = null
    var rotateAnimator: ObjectAnimator? = null
    var switchButton: Switch? = null
    var seekBar: SeekBar? = null
    val SPEED = intArrayOf(0, 5000, 3000, 1000)
    var gd = GradientDrawable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchButton = findViewById(R.id.saklar)
        toggleButton = findViewById(R.id.tombol)
        imageView = findViewById(R.id.kipas)
        seekBar = findViewById(R.id.seekBar)

        rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        rotateAnimator?.setDuration(1000)
        rotateAnimator!!.setRepeatCount(ValueAnimator.INFINITE)
        rotateAnimator?.interpolator = LinearInterpolator()

        toggleButton?.setOnCheckedChangeListener({ _, checked ->
            if (checked) {
                rotateAnimator?.setDuration(SPEED[seekBar!!.progress].toLong())
                rotateAnimator?.start()
            } else {
                rotateAnimator?.end()
            }
        })


        seekBar?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            //rotate the fan based on progress parameter
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        gd.setShape(GradientDrawable.RECTANGLE)
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT)
        gd.setGradientRadius(330F)

        switchButton?.setOnCheckedChangeListener({ _, checked -> //Mengecek Status, Apakah SwitchButton Dalam Keadaan Checked/Hidup atau Unchecked/Mati
            if (checked) {
                gd.colors = intArrayOf(Color.YELLOW, Color.TRANSPARENT)
                imageView?.background = gd
//                Toast.makeText(this,"TEXT")
            } else {
                imageView?.setBackgroundColor(Color.TRANSPARENT)
            }
        })

    }
}