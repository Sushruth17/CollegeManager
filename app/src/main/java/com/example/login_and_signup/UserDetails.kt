package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.adapters.UserDataAdapter
import com.example.login_and_signup.model.StudentModel
import com.example.login_and_signup.model.UserDataModel
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        val buttonCreateUser = findViewById<FloatingActionButton>(R.id.btn_create_user)
        if (buttonCreateUser != null) {
            buttonCreateUser.setOnClickListener {
                Log.i("btntest", "Clicked create user button ")
                val intent = Intent(this, CreateUser::class.java)
                intent.putExtra("buttonsActivity",StringUtils.CREATE_USER)
                startActivity(intent)
            }
        }

        val swipeController = SwipeController(object : SwipeControllerActions() {

            override fun onLeftClicked(position: Int) {
                val dataSelected = user_adapter.data.infoUser?.get(position)
//                val dataSelected = student_adapter.data
                Log.i("dataselected", "-----selected position-----" + position)
                Log.i("dataselected", "-----selected-----" + dataSelected)
                Log.i("btntest", "Clicked ChangeUserRole button ")
                val intent = Intent(context, CreateUser::class.java)
                intent.putExtra("buttonsActivity",StringUtils.CHANGE_USER_ROLE)
                intent.putExtra(StringUtils.USER_DATA,dataSelected)
                startActivity(intent)
            }

            override fun onRightClicked(position: Int) {}
        })

        val itemTouchhelper = ItemTouchHelper(swipeController)
        itemTouchhelper.attachToRecyclerView(rv_userData_list)

        rv_userData_list.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })
    }
}