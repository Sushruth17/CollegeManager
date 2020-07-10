package com.example.login_and_signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search_student.*
import kotlinx.android.synthetic.main.activity_signup.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Signup : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val buttonSignup = findViewById<Button>(R.id.activity_sign_up_button)
        buttonSignup.setOnClickListener{

            Log.i("add","--------clicked button-------------- " )
            val name = sign_up_name.getText().toString()
            Log.i("add", "--------name-------------- $name")
            val username = sign_up_username.getText().toString()
            Log.i("add", "--------username-------------- $username")
            val emailId = sign_up_email_id.getText().toString()
            Log.i("add", "--------emailId-------------- $emailId")
            val password : String = sign_up_password.getText().toString()
            Log.i("add", "--------password-------------- $password")
            val confirmPassword : String = sign_up_confirm_password.getText().toString()
            Log.i("add", "--------confirmPassword-------------- $confirmPassword")


            val jsonObj = JsonObject()
            jsonObj.addProperty("name", name)
            jsonObj.addProperty("username", username)
            jsonObj.addProperty("email id", emailId)
            jsonObj.addProperty("password", password)
            jsonObj.addProperty("confirm password", confirmPassword)
            //  POST demo
            ApiStudent()
                .addRetroFit()
                ?.signUp(jsonObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            println("---TTTT :: POST msg from server :: " + msg)
                            if (msg == "User Created Successfully")
                            {
                                val intent = Intent(context, MainActivity::class.java)
                                startActivity(intent)
                            }
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
        }
}