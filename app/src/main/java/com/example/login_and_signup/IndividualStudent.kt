package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.adapters.StudentMarksAdapter
import com.example.login_and_signup.model.FeesDataModel
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.StudentMarksModel
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_individual_student.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class IndividualStudent : AppCompatActivity() {
    lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_student)

        indv_std_toolbar.setNavigationIcon(R.drawable.ic_back)
        indv_std_toolbar.setNavigationOnClickListener(View.OnClickListener { // Your code
            finish()
        })


        val itemReceived =intent.getParcelableExtra<InfoItem>(StringUtils.STUDENT_INFO_DATA)
        Log.i("Data","----Data received-----"+ itemReceived)
        name_individual_student.text = itemReceived?.name
        branch_indv.text = itemReceived?.branch
        val studentid = itemReceived?.id
        details_age.text = itemReceived.age.toString()
        details_address.text = itemReceived.address
        details_parent_name.text = itemReceived.parentname
        details_branch.text = itemReceived.branch
//        val btnMarks = findViewById<Button>(R.id.btn_marks)
        val progressBar: ProgressBar = this.progress_bar_indv_std
        progressBar.visibility = View.VISIBLE
        main_layout.visibility = View.GONE
//        btnMarks.setOnClickListener{

//                val intent = Intent(context, StudentMarks::class.java)

            ApiStudent()
                .addRetroFit()
                ?.getMarks(studentid)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        progressBar.visibility = View.GONE
                        indv_std_inner_layout1.visibility = View.VISIBLE
                        indv_std_inner_layout2.visibility = View.VISIBLE
                        Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>,
                                            response: Response<ResponseBody>
                    ) {
                        progressBar.visibility = View.GONE
                        main_layout.visibility = View.VISIBLE
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
                            if (msg != "") {
                                val intent = Intent(context, StudentMarks::class.java)
                                view_more_marks_txt.setOnClickListener {
                                    intent.putExtra(StringUtils.STUDENT_MARKS_DATA, msg)
                                    intent.putExtra(StringUtils.STUDENT_INFO_DATA, itemReceived)
                                    startActivity(intent)
                                }
                                Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
//                                Toast.makeText(context, "Successfully" + msg, Toast.LENGTH_SHORT).show()
                                val gson = Gson()
                                val type: Type =
                                    object : TypeToken<StudentMarksModel>() {}.type
                                val studentMarksData = gson.fromJson<StudentMarksModel>(msg, type)
                                Log.i("marksssssss", "ssiizzeeeeee-->$studentMarksData")
                                val rv_studentMarks_list = findViewById<RecyclerView>(R.id.rv_studentMarks_list_indv)
                                val marks_adapter = StudentMarksAdapter()
                                marks_adapter.setDataCustom(studentMarksData)
                                rv_studentMarks_list.layoutManager = LinearLayoutManager(
                                    context, LinearLayoutManager.VERTICAL ,false)
                                rv_studentMarks_list.adapter = marks_adapter
                            }
                            else {
                                Toast.makeText(
                                    context,
                                    "data not available for this student" + msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
//        }

//        val btnFees = findViewById<Button>(R.id.btn_fees)
//        btnFees.setOnClickListener{
//        progressBar.visibility = View.VISIBLE
//        indv_std_inner_layout1.visibility = View.GONE
//        indv_std_inner_layout2.visibility = View.GONE
            ApiStudent()
                .addRetroFit()
                ?.getFeeDetails(studentid)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {


                        Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>,
                                            response: Response<ResponseBody>
                    ) {

                        if (response.isSuccessful) {

                            val msg = response.body()?.string()
                            Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
                            if (msg != "None") {
                                val gson = Gson()
                                val type: Type =
                                    object : TypeToken<FeesDataModel>() {}.type
                                val feesData = gson.fromJson<FeesDataModel>(msg, type)
                                status_fee.text = feesData.feesStatus
                            }
                            else
                                status_fee.text = "No Data"
                                view_more_fees_txt.setOnClickListener {
                                    if (msg != "None") {
                                        val intent = Intent(context, StudentFees::class.java)
                                        intent.putExtra(StringUtils.STUDENT_FEES_DATA, msg)
                                        intent.putExtra(StringUtils.STUDENT_INFO_DATA, itemReceived)
                                        Log.i(
                                            "marksssssss",
                                            "---TTTT :: GET msg from server :: " + msg
                                        )
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                        startActivity(intent)
                                    } else {
                                        /**/ Toast.makeText(
                                            context,
                                            "data not available for this student" + msg,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val builder = AlertDialog.Builder(context)
                                        //set title for alert dialog
                                        builder.setTitle("Do you want to add fees data?")
                                        //set message for alert dialog
                                        builder.setMessage("Fees data not available for this studente")
//                                builder.setIcon(android.R.drawable.ic_dialog_alert)

                                        //performing positive action
                                        builder.setPositiveButton("Yes") { dialogInterface, which ->
                                            Toast.makeText(
                                                applicationContext,
                                                "clicked yes",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            val intent = Intent(context, AddFeeData::class.java)
                                            intent.putExtra(
                                                StringUtils.STUDENT_INFO_DATA,
                                                itemReceived
                                            )
                                            startActivity(intent)
                                        }

                                        builder.setNegativeButton("No") { dialogInterface, which ->
                                            Toast.makeText(
                                                applicationContext,
                                                "clicked No",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            progressBar.visibility = View.GONE
/*                                            indv_std_inner_layout1.visibility = View.VISIBLE
                                            indv_std_inner_layout2.visibility = View.VISIBLE*/
                                        }
                                        // Create the AlertDialog
                                        val alertDialog: AlertDialog = builder.create()
                                        // Set other dialog properties
                                        alertDialog.setCancelable(false)
                                        alertDialog.show()
                                    }
                                }
                    }
                }
        })

//
//    }

        indv_std_inner_layout3_docs.setOnClickListener {
            val intent = Intent(context, Documents::class.java)
            startActivity(intent)
        }
}
/*
    override fun onStart() {
        super.onStart()
        val progressBar: ProgressBar = this.progress_bar_indv_std
        progressBar.visibility = View.GONE
        indv_std_inner_layout1.visibility = View.VISIBLE
        indv_std_inner_layout2.visibility = View.VISIBLE
    }*/

}