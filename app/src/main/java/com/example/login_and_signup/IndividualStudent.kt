package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.login_and_signup.adapters.StudentInfoAdapter
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.model.MockData.Companion.data
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import kotlinx.android.synthetic.main.activity_individual_student.*
import kotlinx.android.synthetic.main.activity_student_marks.*
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
        val itemReceived =intent.getParcelableExtra<InfoItem>(StringUtils.STUDENT_INFO_DATA)
        Log.i("Data","----Data received-----"+ itemReceived)
        name_individual_student.text = itemReceived?.name

        val studentid = itemReceived?.id
        val btnMarks = findViewById<Button>(R.id.btn_marks)
        btnMarks.setOnClickListener{

//                val intent = Intent(context, StudentMarks::class.java)
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
                                Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
                                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                                startActivity(intent)
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

    }
}