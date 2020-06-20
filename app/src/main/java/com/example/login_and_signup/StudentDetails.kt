package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.model.StudentInfoModel
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class StudentDetails : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
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

        rv_studentInfo_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_studentInfo_list.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL ,false)
        rv_studentInfo_list.adapter = student_adapter

        val swipeDelHandler = object : SwipeToDeleteCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rv_studentInfo_list.adapter as StudentInfoAdapter
                adapter.removeAt(viewHolder.adapterPosition)

//                Turrr()
//                    .addRetroFit()
//                    .deleteStudent(viewHolder.adapterPosition)
/*                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            if (response.isSuccessful) {
//                            val msg = "{info:" + response.body()?.string() + "}"
//                            Log.i("api","msgg " + msg)
                                val msg = response.body()?.string()
                                val intent = Intent(context, StudentDetails::class.java)
                                intent.putExtra(StringUtils.STUDENT_INFO_DATA, msg)
                                startActivity(intent)
                                Log.i("api", "---TTTT :: GET msg from server :: " + msg)
//                            Toast.makeText(context, "Im the msg" +  msg, Toast.LENGTH_SHORT).show()

                            }
                        }
                    })*/
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeDelHandler)
        itemTouchHelper.attachToRecyclerView(rv_studentInfo_list)


        val swipeEditHandler = object : SwipetoEditCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = rv_studentInfo_list.adapter as StudentInfoAdapter
                adapter.edit(viewHolder)
            }
        }

        val itemEditHelper = ItemTouchHelper(swipeEditHandler)
        itemEditHelper.attachToRecyclerView(rv_studentInfo_list)




//        addItemBtn.setOnClickListener(this)
//    }
//
//    override fun onClick(v: View) {
//        when (v.id) {
//            R.id.addItemBtn -> simpleAdapter.addItem("New item")
//        }
//    }


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

//private fun <VH : RecyclerView.ViewHolder?> RecyclerView.Adapter<VH>?.removeAt(adapterPosition: Int) {
//    data.removeAt(adapterPosition)
//    this?.notifyItemRemoved(adapterPosition)
//}




