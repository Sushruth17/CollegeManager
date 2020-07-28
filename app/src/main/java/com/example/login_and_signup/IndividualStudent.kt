package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.MockData.Companion.data
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import kotlinx.android.synthetic.main.activity_edit_student.*
import kotlinx.android.synthetic.main.activity_individual_student.*
import kotlinx.android.synthetic.main.activity_student_marks.*
import kotlinx.android.synthetic.main.fragment_home1.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val btnMarks = findViewById<Button>(R.id.btn_marks)
        val progressBar: ProgressBar = this.progress_bar_indv_std
        btnMarks.setOnClickListener{

//                val intent = Intent(context, StudentMarks::class.java)

            progressBar.visibility = View.VISIBLE
            indv_std_inner_layout.visibility = View.GONE
            ApiStudent()
                .addRetroFit()
                ?.getMarks(studentid)
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
                            if (msg != "") {
                                val intent = Intent(context, StudentMarks::class.java)
                                intent.putExtra(StringUtils.STUDENT_MARKS_DATA, msg)
                                intent.putExtra(StringUtils.STUDENT_INFO_DATA, itemReceived)
                                startActivity(intent)
                                Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
                                Toast.makeText(
                                    context,
                                    "Successfully" + msg,
                                    Toast.LENGTH_SHORT
                                ).show()
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
        }

        val btnFees = findViewById<Button>(R.id.btn_fees)
        btnFees.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            indv_std_inner_layout.visibility = View.GONE
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
                                val intent = Intent(context, StudentFees::class.java)
                                intent.putExtra(StringUtils.STUDENT_FEES_DATA ,msg)
                                intent.putExtra(StringUtils.STUDENT_INFO_DATA, itemReceived)
                                Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
                                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                            }
                            else {
                               /**/ Toast.makeText(context, "data not available for this student" + msg, Toast.LENGTH_SHORT).show()
                                val builder = AlertDialog.Builder(context)
                                //set title for alert dialog
                                builder.setTitle("Do you want to add fees data?")
                                //set message for alert dialog
                                builder.setMessage("Fees data not available for this studente")
//                                builder.setIcon(android.R.drawable.ic_dialog_alert)

                                //performing positive action
                                builder.setPositiveButton("Yes"){dialogInterface, which ->
                                    Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
                                    val intent = Intent(context, AddFeeData::class.java)
                                    intent.putExtra(StringUtils.STUDENT_INFO_DATA, itemReceived)
                                    startActivity(intent)
                            }

                                builder.setNegativeButton("No"){dialogInterface, which ->
                                    Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                                    progressBar.visibility = View.GONE
                                    indv_std_inner_layout.visibility = View.VISIBLE
                                }
                                // Create the AlertDialog
                                val alertDialog: AlertDialog = builder.create()
                                // Set other dialog properties
                                alertDialog.setCancelable(false)
                                alertDialog.show()
                        }
                    }
                }
        })

    }
}

    override fun onStart() {
        super.onStart()
        val progressBar: ProgressBar = this.progress_bar_indv_std
        progressBar.visibility = View.GONE
        indv_std_inner_layout.visibility = View.VISIBLE
    }
}