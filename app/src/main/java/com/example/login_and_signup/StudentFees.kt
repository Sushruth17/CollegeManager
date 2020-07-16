package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.Toast
import com.example.login_and_signup.model.FeesDataModel
import com.example.login_and_signup.model.ProfileDataModel
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_student_fees.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class StudentFees : AppCompatActivity() {
    lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_fees)
        val json = intent.getStringExtra(StringUtils.STUDENT_FEES_DATA) ?: StringUtils.NOT_VALID
        val gson = Gson()
        Log.i("fees_data", "json -->$json")
        val type: Type =
            object : TypeToken<FeesDataModel>() {}.type
        val feesData = gson.fromJson<FeesDataModel>(json, type)
        Log.i("fees_data", "fees_data-->$feesData")

        amount_tobe_paid_edit_txt.text = feesData.amountToBePaid
        amount_paid_edit_txt.text = feesData.amountPaid
        np_amount_tobe_paid_edit_txt.setText(feesData.amountToBePaid)
        np_amount_paid_edit_txt.setText(feesData.amountPaid)
        amount_due_edit_txt.setText(feesData.amountDue)

        if (feesData.feesStatus == StringUtils.PAID){
            checkBoxPaid.isChecked = true
            checkBoxNotPaid.visibility = View.GONE
            amount_tobe_paid_ll.visibility = View.VISIBLE
            amount_paid_ll.visibility = View.VISIBLE

        }

        if (feesData.feesStatus == StringUtils.NOT_PAID){
            checkBoxNotPaid.isChecked = true
            checkBoxPaid.visibility = View.GONE
            np_amount_tobe_paid_ll.visibility = View.VISIBLE
            np_amount_paid_ll.visibility = View.VISIBLE
            amount_due_ll.visibility = View.VISIBLE
            btn_send_remainder.visibility = View.VISIBLE
            btn_update_fees.visibility = View.VISIBLE
        }


        checkBoxPaid.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Paid", Toast.LENGTH_SHORT).show()
                checkBoxNotPaid.visibility = View.GONE
                amount_tobe_paid_ll.visibility = View.VISIBLE
                amount_paid_ll.visibility = View.VISIBLE
            }
            else {
                checkBoxNotPaid.visibility = View.VISIBLE
                amount_tobe_paid_ll.visibility = View.GONE
                amount_paid_ll.visibility = View.GONE
            }
        }

        checkBoxNotPaid.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, " Not Paid", Toast.LENGTH_SHORT).show()
                checkBoxPaid.visibility = View.GONE
                np_amount_tobe_paid_ll.visibility = View.VISIBLE
                np_amount_paid_ll.visibility = View.VISIBLE
                amount_due_ll.visibility = View.VISIBLE
                btn_send_remainder.visibility = View.VISIBLE
                btn_update_fees.visibility = View.VISIBLE
            }
            else {
                checkBoxPaid.visibility = View.VISIBLE
                np_amount_tobe_paid_ll.visibility = View.GONE
                np_amount_paid_ll.visibility = View.GONE
                amount_due_ll.visibility = View.GONE
                btn_send_remainder.visibility = View.GONE
                btn_update_fees.visibility = View.GONE
            }
        }

        checkBoxConcession.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Concession", Toast.LENGTH_SHORT).show()
                concession_amount_ll.visibility = View.VISIBLE
            } else {
                concession_amount_ll.visibility = View.GONE
            }
        }

        val btnUpdateFees = findViewById<Button>(R.id.btn_update_fees)
        btnUpdateFees.setOnClickListener {

            val jsonUpdateFeesObj = JsonObject()
            if (checkBoxNotPaid.isChecked) {
                val np_amountToBePaid = np_amount_tobe_paid_edit_txt.text.toString()
                val np_amountPaid = np_amount_paid_edit_txt.text.toString()
                val np_amountDue = amount_due_edit_txt.text.toString()

                jsonUpdateFeesObj.addProperty("np_amountToBePaid", np_amountToBePaid)
                jsonUpdateFeesObj.addProperty("np_amountPaid", np_amountPaid)
                jsonUpdateFeesObj.addProperty("np_amountDue", np_amountDue)
                if (np_amountDue == "0")
                    jsonUpdateFeesObj.addProperty("feesStatus",StringUtils.PAID)
                else
                    jsonUpdateFeesObj.addProperty("feesStatus",StringUtils.NOT_PAID)
                jsonUpdateFeesObj.addProperty("studentId",feesData.sid)
            }

            ApiStudent()
                .addRetroFit()
                ?.updateFeesData(jsonUpdateFeesObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.i("api", "---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                    }
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            Log.i("marksssssss", "---TTTT :: GET msg from server :: " + msg)
                            Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                })
        }

        val btnSendRemainder = findViewById<Button>(R.id.btn_send_remainder)
        btnSendRemainder.setOnClickListener {
            Toast.makeText(this, "Remainder sent successfully", Toast.LENGTH_SHORT).show()
        }
    }
}