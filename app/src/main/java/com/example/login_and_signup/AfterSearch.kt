package com.example.login_and_signup


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.StudentInfoModel
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class AfterSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_search)
        val json = intent.getStringExtra(StringUtils.STUDENT_SEARCH_DATA)
        val gson = Gson()

        Log.i("myyyaapp", "josn -->$json")
//        if (json != "{'info': ()}") {
            val type: Type =
                object : TypeToken<StudentInfoModel>() {}.type
            val searchInfo = gson.fromJson<StudentInfoModel>(json, type)
            Log.i("myyyaapp", "ssiizzeeeeee-->$searchInfo")
            val rv_searchInfo_list = findViewById<RecyclerView>(R.id.rv_searchInfo_list)
            val search_adapter = StudentInfoAdapter()
            search_adapter.setDataCustom(searchInfo)

            rv_searchInfo_list.layoutManager = LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
            )
            rv_searchInfo_list.adapter = search_adapter

//        val myTextView = findViewById(R.id.name_as) as TextView
//        myTextView.text = searchInfo.info.toString()
//        }
//        else  Toast.makeText(this, "Student DO not Exits", Toast.LENGTH_SHORT).show()
    }
}

