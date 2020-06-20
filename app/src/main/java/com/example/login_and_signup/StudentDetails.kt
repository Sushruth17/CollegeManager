package com.example.login_and_signup

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.model.StudentInfoModel
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class StudentDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Learning","------ Home activity B on create --------- ")
        Log.i("api","------ After start activity Button clicked --------- ")
//        Log.i("here","------this is apikindastuff--------" + apiKindaStuff)
        setContentView(R.layout.activity_std_details)
        val json = intent.getStringExtra(StringUtils.STUDENT_INFO_DATA)
        val gson = Gson()


        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            val intent = Intent(this, AddStudent::class.java)
            startActivity(intent)
        }


        Log.i("myyyaapp", "josn -->$json")
        val type: Type =
            object : TypeToken<StudentInfoModel>() {}.type
        val studentInfoData = gson.fromJson<StudentInfoModel>(json, type)
        Log.i("myyyaapp", "ssiizzeeeeee-->$studentInfoData")


        val rv_studentInfo_list = findViewById<RecyclerView>(R.id.rv_studentInfo_list)
        val student_adapter = StudentInfoAdapter()
        student_adapter.setDataCustom(studentInfoData)

        rv_studentInfo_list.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL ,false)
        rv_studentInfo_list.adapter = student_adapter




        val swipeController = SwipeController(object : SwipeControllerActions() {
            override fun onRightClicked(position: Int) {
                student_adapter.removeAt(position)
                student_adapter.notifyItemRemoved(position)
                student_adapter.notifyItemRangeChanged(position, student_adapter.getItemCount())
            }
        })


        val itemTouchhelper = ItemTouchHelper(swipeController)
        itemTouchhelper.attachToRecyclerView(rv_studentInfo_list)

        rv_studentInfo_list.addItemDecoration(object : ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })
    }

    

    override fun onPause() {
        super.onPause()
        Log.i("Learning","------ Home activity B on pause --------- ")
    }


    override fun onStop() {
        super.onStop()
        Log.i("Learning","------ Home activity B on stop --------- ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Learning","------ Home activity B on destroy --------- ")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Learning","------ Home activity B on start --------- ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Learning","------ Home activity B on restart --------- ")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Learning","------ Home activity B on resume --------- ")
    }


}