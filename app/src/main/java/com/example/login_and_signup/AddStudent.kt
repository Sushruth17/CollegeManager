package com.example.login_and_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.login_and_signup.utils.ApiAddStudent
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_search_student.*
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

            val name_added : String = add_name.getText().toString()
            Log.i("add","--------ADDNAME-------------- " + name_added)
            val address_added : String = add_address.getText().toString()
            Log.i("add","--------ADDADDRES-------------- " + address_added)
            val age_added : String = add_age.getText().toString()
            Log.i("add","--------ADDAGE-------------- " + age_added)
            val parentname_added : String = add_parent_name.getText().toString()
            Log.i("add","--------ADDPARENTNAME-------------- " + parentname_added)


            val jsonObj = JsonObject()
            jsonObj.addProperty("name", name_added)
            jsonObj.addProperty("address", address_added)
            jsonObj.addProperty("age", age_added)
            jsonObj.addProperty("parentname", parentname_added)
            //  POST demo
            ApiAddStudent()
                .addRetroFit()
                ?.getVectors(jsonObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            println("---TTTT :: POST msg from server :: " + msg)
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            val intent = Intent(this, Home::class.java)
            startActivity(intent)

        }
    }
}