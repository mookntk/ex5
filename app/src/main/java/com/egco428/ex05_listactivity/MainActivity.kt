package com.egco428.ex05_listactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_detail.view.imageCourse
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val imgfile = listOf<Int>(R.drawable.image10101,R.drawable.image10102,R.drawable.image10103)
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataProvider.getData()
        //val iterator = data.iterator()
        //val builder = StringBuilder()//empty text

        /*while(iterator.hasNext()){
            val course = iterator.next()
            builder.append(course.title).append("\n")
        }
        courseListView.text = builder.toString()*/

        list.adapter = CourseArrayAdapter(this,1,data)

        //val courseArrayAdapter = ArrayAdapter<Course>(this,android.R.layout.simple_list_item_1,data)
        //list.setAdapter(courseArrayAdapter)

        list.setOnItemClickListener { adapterView, view, position, id ->
            val course = data.get(position)
//            Log.d("Course Catelog","Course: ${course.title}")
            i = position
            displayDetail(course)
        }

    }

    private fun displayDetail(course: Course){
        Log.d("Course Catelog","Course: ${course.title}")
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("courseTitle",course.title)
        intent.putExtra("courseDesc",course.description)
        intent.putExtra("courseNo",course.courseNumber)
        intent.putExtra("credits",course.credits)
        intent.putExtra("image",imgfile[i%3])

        startActivity(intent)
    }

    private class CourseArrayAdapter(var context: Context, var resource: Int,var objects: ArrayList<Course>):BaseAdapter(){
        val imgfile = listOf<Int>(R.drawable.image10101,R.drawable.image10102,R.drawable.image10103)
        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return objects.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val courseItem: View
            if(convertView == null){
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)//!!=not null
                courseItem = layoutInflator.inflate(R.layout.course_item, viewGroup, false)
                val viewHolder = ViewHolder(courseItem.titletext,courseItem.imageCourse)
                courseItem.tag = viewHolder
            }else{
                courseItem = convertView
            }
            val img = position%3+1
            val viewHolder = courseItem.tag as ViewHolder
            viewHolder.titleText.text = objects.get(position).title
//            viewHolder.titleText.text = "image1010"+img
//            context.getDrawable(position)

            val i = position%3
            viewHolder.imageCourse.setImageResource(imgfile[i])
//            val img = findViewById(R.id.image) as ImageView
//            img.setImageResource(R.drawable.my_image)
//            viewHolder.imageCourse = ImageView(context)

            return courseItem
        }

    }
    private class ViewHolder(val titleText: TextView, val imageCourse: ImageView){

    }
}
