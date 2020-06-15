package com.example.login_and_signup

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.model.StudentInfoModel
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.sql.Wrapper


class StudentDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_std_details)

        val json = intent.getStringExtra(StringUtils.STUDENT_INFO_DATA)
        val gson = Gson()


        val type: Type =
            object : TypeToken<StudentInfoModel>() {}.type
        val studentInfoData = gson.fromJson<StudentInfoModel>(json, type)
        Log.i("myyyaapp", "ssiizzeeeeee-->$studentInfoData")

        val rv_studentInfo_list = findViewById<RecyclerView>(R.id.rv_studentInfo_list)
        val student_adapter = StudentInfoAdapter()
        student_adapter.setDataCustom(studentInfoData)

        rv_studentInfo_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        rv_studentInfo_list.adapter = student_adapter
//        val button= findViewById<Button>(R.id.add_std)
//        button.setOnClickListener {
//            val intent = Intent(this, AddStudent::class.java)
//            startActivity(intent)
//        }
    }

}