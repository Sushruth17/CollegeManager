package com.example.login_and_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_particular_year.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUser : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val spinnerCreateUser: Spinner? = findViewById(R.id.create_user_spinner)
        val arrayCreateUser = R.array.create_user_array

        if (spinnerCreateUser != null) {
            spinnerCreateUser.onItemSelectedListener = this
        }

        applicationContext.let {
            ArrayAdapter.createFromResource(
                it,
                arrayCreateUser,
                R.layout.spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                if (spinnerCreateUser != null) {
                    spinnerCreateUser.adapter = adapter
                }

            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val spinnerCreateUser: String = create_user_spinner.selectedItem.toString()
        Log.i("spinner","---selected ROLE---"+spinnerCreateUser)

        val buttonCreate = findViewById<Button>(R.id.btn_create)
        buttonCreate.setOnClickListener {
            val emailIdForCreateUser = create_user_input_email_id.getText().toString()
            Log.i("emailid", "--------emailId-------------- $emailIdForCreateUser")
            if (!StringUtils.checkRegex(emailIdForCreateUser, StringUtils.EMAIL_PATTERN)) {
                Toast.makeText(applicationContext, "Invalid email address", Toast.LENGTH_SHORT)
                    .show()
            }

            val jsonUserObj = JsonObject()
            jsonUserObj.addProperty("userType", spinnerCreateUser)
            jsonUserObj.addProperty("emailId", emailIdForCreateUser)


            ApiStudent()
                .addRetroFit()
                ?.addUser(jsonUserObj)
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
                        }
                    }
                })


        }

    }
}