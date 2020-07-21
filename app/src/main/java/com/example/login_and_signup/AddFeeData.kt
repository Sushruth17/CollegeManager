package com.example.login_and_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_fee_data.*
import kotlinx.android.synthetic.main.activity_add_student.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFeeData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_fee_data)

        val itemReceived =intent.getParcelableExtra<InfoItem>(StringUtils.STUDENT_INFO_DATA)

        addFeetext.text = """${itemReceived.name}'s Fee Data"""
        val studentid = itemReceived?.id
        val btnAddFee = findViewById<Button>(R.id.btnAddFee)
        btnAddFee.setOnClickListener {

            val actualFee  = addFee_actualFee.text.toString()
            val concession= addFee_concession.text.toString()
            val amountTBePaid  = addFee_amountToBePaid.text.toString()
            val amountPaid  = addFee_amountPaid.text.toString()
            val amountDue  = addFee_amountDue.text.toString()



            val jsonAddFeeObj = JsonObject()
            jsonAddFeeObj.addProperty("StudentId", studentid)
            jsonAddFeeObj.addProperty("ActualFee", actualFee)
            jsonAddFeeObj.addProperty("Concession", concession)
            jsonAddFeeObj.addProperty("AmountTBePaid", amountTBePaid)
            jsonAddFeeObj.addProperty("AmountPaid", amountPaid)
            jsonAddFeeObj.addProperty("AmountDue", amountDue)
            //  POST demo
            ApiStudent()
                .addRetroFit()
                ?.addFee(jsonAddFeeObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.i("api","---TTTT :: POST Throwable EXCEPTION :: "+ t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            Log.i("api", "---TTTT :: GET msg from server :: " + msg)
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                })
        }
    }
}