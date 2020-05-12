package com.abcd.firebasemlkit02

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.abcd.firebasemlkit02.camera_image.ui.ImageParseActivity
import com.abcd.firebasemlkit02.image_contour.ui.ImageActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lstMenu.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arrayOf("Image Processing", "Camera Image Processing")
        )

        lstMenu.setOnItemClickListener { adapterView, view, position, l ->
            when (position) {
                0 -> startActivity(Intent(this, ImageActivity::class.java))
                1 -> startActivity(Intent(this, ImageParseActivity::class.java))
            }
        }
    }
}
