package com.example.login_and_signup


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.adapters.StudentMarksAdapter
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.StudentMarksModel
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_individual_student.*
import kotlinx.android.synthetic.main.activity_std_details.*
import kotlinx.android.synthetic.main.activity_student_marks.*
import kotlinx.android.synthetic.main.student_marks_unit.*
import java.lang.reflect.Type


class StudentMarks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_marks)

        indv_marks_toolbar.setNavigationIcon(R.drawable.ic_back)
        indv_marks_toolbar.setNavigationOnClickListener(View.OnClickListener { // Your code
            finish()
        })

        val itemReceived =intent.getParcelableExtra<InfoItem>(StringUtils.STUDENT_INFO_DATA)
        Log.i("Data","----Data received-----"+ itemReceived)
        indv_marks_name.text = itemReceived?.name + "'s Marks"

        val json = intent.getStringExtra(StringUtils.STUDENT_MARKS_DATA)
        val gson = Gson()
        Log.i("marksssssss", "json -->$json")

        val type: Type =
            object : TypeToken<StudentMarksModel>() {}.type
        val studentMarksData = gson.fromJson<StudentMarksModel>(json, type)
        Log.i("marksssssss", "ssiizzeeeeee-->$studentMarksData")


        val rv_studentMarks_list = findViewById<RecyclerView>(R.id.rv_studentMarks_list)


        val marks_adapter = StudentMarksAdapter()
        marks_adapter.setDataCustom(studentMarksData)

        rv_studentMarks_list.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL ,false)
        rv_studentMarks_list.adapter = marks_adapter

    }
}
