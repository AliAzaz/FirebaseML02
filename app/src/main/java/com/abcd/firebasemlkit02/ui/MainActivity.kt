package com.abcd.firebasemlkit02.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.abcd.firebasemlkit02.ImageActivity
import com.abcd.firebasemlkit02.R
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
            }
        }
    }
}