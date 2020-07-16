package com.example.login_and_signup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.Toast
import com.example.login_and_signup.model.FeesDataModel
import com.example.login_and_signup.model.ProfileDataModel
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_student_fees.*
import java.lang.reflect.Type

class StudentFees : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_fees)
        val json = intent.getStringExtra(StringUtils.STUDENT_FEES_DATA) ?: StringUtils.NOT_VALID
        val gson = Gson()
        Log.i("fees_data", "json -->$json")
        val type: Type =
            object : TypeToken<FeesDataModel>() {}.type
        val feesData = gson.fromJson<FeesDataModel>(json, type)
        Log.i("fees_data", "fees_data-->$feesData")

        amount_tobe_paid_edit_txt.setText(feesData.amountToBePaid)
        amount_paid_edit_txt.setText(feesData.amountPaid)
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
            }
            else {
                checkBoxPaid.visibility = View.VISIBLE
                np_amount_tobe_paid_ll.visibility = View.GONE
                np_amount_paid_ll.visibility = View.GONE
                amount_due_ll.visibility = View.GONE
                btn_send_remainder.visibility = View.GONE
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

    }
}