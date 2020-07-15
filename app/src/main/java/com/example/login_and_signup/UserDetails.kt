package com.example.login_and_signup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.adapters.UserDataAdapter
import com.example.login_and_signup.model.StudentModel
import com.example.login_and_signup.model.UserDataModel
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class UserDetails : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        context = this
        val json = intent.getStringExtra(StringUtils.USER_DATA)
        val gson = Gson()
        Log.i("myyyaapp", "josn -->$json")
        val type: Type =
            object : TypeToken<UserDataModel>() {}.type
        val userData = gson.fromJson<UserDataModel>(json, type)
        Log.i("myyyaapp", "ssiizzeeeeee-->$userData")
        val rv_userData_list = findViewById<RecyclerView>(R.id.rv_userData_list)


        val user_adapter = UserDataAdapter()
        user_adapter.setDataCustom(userData)

        rv_userData_list.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL ,false)
        rv_userData_list.adapter = user_adapter
    }
}