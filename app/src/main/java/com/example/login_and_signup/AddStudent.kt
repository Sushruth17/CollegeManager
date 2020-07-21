package com.example.login_and_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.login_and_signup.utils.ApiStudent
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_student.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val button = findViewById<Button>(R.id.add)
        button.setOnClickListener {

            val name_added: String = add_name.text.toString()
            Log.i("add", "--------ADDNAME-------------- " + name_added)
            val address_added: String = add_address.text.toString()
            Log.i("add", "--------ADDADDRES-------------- " + address_added)
            val age_added: String = add_age.text.toString()
            Log.i("add", "--------ADDAGE-------------- " + age_added)
            val parentname_added: String = add_parent_name.text.toString()
            Log.i("add", "--------ADDPARENTNAME-------------- " + parentname_added)
            val branch = add_branch.text.toString()
            Log.i("add", "--------ADDBRANCCH-------------- " + add_branch)

            if ((branch != "CSE") and (branch != "ISE") and (branch != "ECE")) {
                Toast.makeText(applicationContext, "Invalid branch", Toast.LENGTH_SHORT).show()
            } else {
                val jsonObj = JsonObject()
                jsonObj.addProperty("name", name_added)
                jsonObj.addProperty("address", address_added)
                jsonObj.addProperty("age", age_added)
                jsonObj.addProperty("parentname", parentname_added)
                jsonObj.addProperty("branch", branch)
                //  POST demo
                ApiStudent()
                    .addRetroFit()
                    ?.getVectors(jsonObj)
                    ?.enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                           Log.i("api","---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                Log.i("api","---TTTT :: POST msg from server :: " + msg)
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                    })
            }
        }
    }
}