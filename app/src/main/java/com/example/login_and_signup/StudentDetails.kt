package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.model.StudentModel
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_std_details.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class StudentDetails : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Learning","------ Home activity B on create --------- ")
        Log.i("api","------ After start activity Button clicked --------- ")
//        Log.i("here","------this is apikindastuff--------" + apiKindaStuff)
        setContentView(R.layout.activity_std_details)

        std_details_toolbar.setNavigationIcon(R.drawable.ic_back)
        std_details_toolbar.setNavigationOnClickListener(View.OnClickListener { // Your code
            finish()
        })

/*        val searchIcon = search_bar.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)

        val cancelIcon = search_bar.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)

        val textView = search_bar.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.WHITE)*/


 /*       search_bar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val adpFilter = StudentInfoAdapter().getFilter()
                if (adpFilter != null) {
                    adpFilter.filter(newText)
                }
                return false
            }

        })*/



        context = this
        val json = intent.getStringExtra(StringUtils.STUDENT_INFO_DATA)
        val gson = Gson()


        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            val intent = Intent(this, AddStudent::class.java)
            startActivity(intent)
        }


        Log.i("myyyaapp", "josn -->$json")
        val type: Type =
            object : TypeToken<StudentModel>() {}.type
        val studentInfoData = gson.fromJson<StudentModel>(json, type)
        Log.i("myyyaapp", "ssiizzeeeeee-->$studentInfoData")
        val rv_studentInfo_list = findViewById<RecyclerView>(R.id.rv_studentInfo_list)


        val student_adapter = StudentInfoAdapter()
        student_adapter.setDataCustom(studentInfoData)

        rv_studentInfo_list.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL ,false)
        rv_studentInfo_list.adapter = student_adapter

        rv_studentInfo_list.addOnItemTouchListener(RecyclerItemClickListenr
            (this, rv_studentInfo_list, object :
            RecyclerItemClickListenr.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                val itemSelected = student_adapter.data.info?.get(position)
                val studentid = itemSelected?.id

                val intent = Intent(context, IndividualStudent::class.java)
                intent.putExtra(StringUtils.STUDENT_INFO_DATA, itemSelected)
                startActivity(intent)

//                val intent = Intent(context, StudentMarks::class.java)
/*                ApiStudent()
                    .addRetroFit()
                    ?.getMarks(studentid)
                    ?.enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>,
                                                response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
                                if (msg != "") {
                                    val intent = Intent(context, StudentMarks::class.java)
                                    intent.putExtra(StringUtils.STUDENT_MARKS_DATA, msg)
                                    intent.putExtra(StringUtils.STUDENT_INFO_DATA, itemSelected)
                                    startActivity(intent)
                                    Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
                                    Toast.makeText(
                                        context,
                                        "Successfully" + msg,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                Toast.makeText(
                                    context,
                                    "data not available for this student" + msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })*/
//                startActivity(intent)
            }
            override fun onItemLongClick(view: View?, position: Int) {
                Log.i("here","-----inside recycler view on long click-----")
                val studentSelected = student_adapter.data.info?.get(position)
                val studentName = studentSelected?.name
                Log.i("long","-----on long click -----"+studentName)
/*                if (studentName != null) {
                    ApiStudent()
                        .addRetroFit()
                        ?.nameSearched(studentName)
                        ?.enqueue(object : Callback<ResponseBody> {
                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.i("api","---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                            }

                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                if (response.isSuccessful) {
                                    val msg = response.body()?.string()
                                    if (msg != "{'info': ()}") {
                                        val intent = Intent(context, AfterSearch::class.java)
                                        intent.putExtra(StringUtils.STUDENT_SEARCH_DATA, msg)
                                        startActivity(intent)
                                        Log.i("api", "---TTTT :: GET msg from server :: " + msg)
            //                                Toast.makeText(context, "Im the msg" + msg, Toast.LENGTH_SHORT)
            //                                    .show()
                                    } else  Toast.makeText(context,"Student does not exist", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                }*/
            }
        }))

        val swipeController = SwipeController(object : SwipeControllerActions() {

            override fun onLeftClicked(position: Int) {
                val dataSelected = student_adapter.data.info?.get(position)
//                val dataSelected = student_adapter.data
                Log.i("dataselected","-----selected position-----"+position)
                Log.i("dataselected","-----selected-----"+dataSelected)
                val intent = Intent(context, EditStudent::class.java)
                intent.putExtra(StringUtils.STUDENT_EDIT_DATA,dataSelected)
                startActivity(intent)
            }
            override fun onRightClicked(position: Int) {

                val builder = AlertDialog.Builder(context)
                //set title for alert dialog
                builder.setTitle("Are you sure you want to delete?")
                //set message for alert dialog
                builder.setMessage("Deleteing student will remove student from the database")
                builder.setIcon(android.R.drawable.ic_dialog_alert)

                //performing positive action
                builder.setPositiveButton("Yes"){dialogInterface, which ->
                    Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()


                    val removed = student_adapter.data.info?.get(position)?.id
                    student_adapter.removeAt(position)
                    student_adapter.notifyItemRemoved(position)
                    student_adapter.notifyItemRangeChanged(position, student_adapter.itemCount)
//                student_adapter.data.info?.get(position)?.id

                    Log.i("del", "------removed----position------>" +position+"\n" +Gson().toJson(removed))
                    ApiStudent()
                        .addRetroFit()
                        ?.deleteStudent(removed)
                        ?.enqueue(object : Callback<ResponseBody> {
                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                            }

                            override fun onResponse(call: Call<ResponseBody>,
                                                    response: Response<ResponseBody>) {
                                if (response.isSuccessful) {
                //                            val msg = "{info:" + response.body()?.string() + "}"
                //                            Log.i("api","msgg " + msg)
                                    val msg = response.body()?.string()
                //                                val intent = Intent(context, StudentDetails::class.java)
                //                                intent.putExtra(StringUtils.STUDENT_INFO_DATA, msg)
                //                                startActivity(intent)
                                    Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                                    Toast.makeText(context, "Successfully" +  msg, Toast.LENGTH_SHORT).show()
                                }
                            }
                        })

                }
                //performing cancel action
                builder.setNeutralButton("Cancel"){dialogInterface , which ->
                    Toast.makeText(applicationContext,"operation cancel",Toast.LENGTH_LONG).show()
                }
                //performing negative action
                builder.setNegativeButton("No"){dialogInterface, which ->
                    Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                }
                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                // Set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()



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
