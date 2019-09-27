package com.egco428.ex05_listactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.setTitle("ex05_ListActivity")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val courseTitle = intent.getStringExtra("courseTitle")
        val courseDesc = intent.getStringExtra("courseDesc")
        val courseNo = intent.getIntExtra("courseNo",0)
        val credits = intent.getDoubleExtra("credits",0.0)
//        val credits = intent.getIntExtra("credits",0.0)


        titletext.text = courseTitle
        descriptionText.text = courseDesc
        txtcourseNo.text = courseNo.toString()
        txtcredit.text = "Credits: "+credits.toString()
        imageCourse.setImageResource(intent.getIntExtra("image",1));
    }
}
