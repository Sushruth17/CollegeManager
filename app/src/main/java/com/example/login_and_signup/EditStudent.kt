package com.example.login_and_signup

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_and_signup.model.InfoItem
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_edit_student.*
import kotlinx.android.synthetic.main.activity_pass_percentage.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        edit_std_toolbar.setNavigationIcon(R.drawable.ic_back)
        edit_std_toolbar.setNavigationOnClickListener(View.OnClickListener { // Your code
            finish()
        })

        val dataReceived =intent.getParcelableExtra<InfoItem>(StringUtils.STUDENT_EDIT_DATA)
        Log.i("Data","----Data received-----"+ dataReceived)

        var age = "0"
        if (dataReceived.age != null )
            age = dataReceived.age.toString()

        edit_name.setText(dataReceived.name)
        edit_age.setText(age)
        edit_address.setText(dataReceived.address)
        edit_parent_name.setText(dataReceived.parentname)
        edit_branch.setText(dataReceived.branch)


        edit_name.filters = validateET("[A-Za-z ]+")
        edit_parent_name.filters = validateET("([A-Za-z ])+")

        val buttonEdit = findViewById<Button>(R.id.btn_edit)
        buttonEdit.setOnClickListener {
            doThis(dataReceived);
        }
    }

    private fun doThis(dataReceived: InfoItem?) {


//            val name_edited: String = edit_name.text.toString()
        val nameEdited = edit_name.text.toString()
        Log.i("edit", "--------nameEdited-------------- " + nameEdited)
        val address_edited: String = edit_address.text.toString()
        Log.i("edit", "--------address_edited-------------- " + address_edited)
        val age_edited: String = edit_age.text.toString()
        Log.i("edit", "--------age_edited-------------- " + age_edited)
        val parentname_edited: String = edit_parent_name.text.toString()
        Log.i("edit", "--------parentname_edited-------------- " + parentname_edited)
        val branch_edited: String = edit_branch.text.toString()
        Log.i("edit", "--------branch_edited-------------- " + branch_edited)
        val idOfEdited = dataReceived?.id
        if ((branch_edited != "CSE") and (branch_edited != "ISE") and (branch_edited != "ECE")) {
            Toast.makeText(applicationContext, "Invalid branch", Toast.LENGTH_SHORT).show()
        } else {

            val jsonEditObj = JsonObject()
            jsonEditObj.addProperty("name", nameEdited)
            jsonEditObj.addProperty("address", address_edited)
            jsonEditObj.addProperty("age", age_edited)
            jsonEditObj.addProperty("parentname", parentname_edited)
            jsonEditObj.addProperty("branch", branch_edited)
            jsonEditObj.addProperty("id", idOfEdited)
            //  POST demo
            ApiStudent()
                .addRetroFit()
                ?.editStudent(jsonEditObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            println("---TTTT :: POST msg from server :: " + msg)
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                })
        }
    }

    private fun validateET(regex: String): Array<InputFilter>? {
        return arrayOf(
            InputFilter { src, start, end, dst, dstart, dend ->
                if (src == "") { // for backspace
                    Log.i("regex","---------REGEX_ if backspace--------")
                    return@InputFilter src

                }
                if (StringUtils.checkRegex(src,regex)){
                    Log.i("regex","---------REGEX_ if given expression--------")
                        src
                    } else ""

            }
        )

    }

}



