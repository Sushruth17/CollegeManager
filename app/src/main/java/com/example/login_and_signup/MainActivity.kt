package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_and_signup.utils.ApiStudent
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    public override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("add","--------inside activity main-------------- " )

        val buttonSignin = findViewById<Button>(R.id.btn_signin)
        buttonSignin.setOnClickListener{

            Log.i("add","--------clicked button-------------- " )
            val username = sign_in_username.getText().toString()
            Log.i("add","--------ADDNAME-------------- " + username)
            val password : String = sign_in_password.getText().toString()
            Log.i("add","--------ADDADDRES-------------- " + password)


            val jsonObj = JsonObject()
            jsonObj.addProperty("username", username)
            jsonObj.addProperty("password", password)
            //  POST demo
            ApiStudent()
                .addRetroFit()
                ?.signIn(jsonObj)
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val msg = response.body()?.string()
                            println("---TTTT :: POST msg from server :: " + msg)
                            if (msg == "Successfully signed in")
                            {
                                val intent = Intent(context, Home::class.java)
                                startActivity(intent)
                            }
                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
        val fotgotPassword = findViewById<TextView>(R.id.forgot_password)
        fotgotPassword.setOnClickListener{
            val intent = Intent(this, ResetPassword::class.java)
            startActivity(intent)
        }

        val btnSignUp = findViewById<Button>(R.id.btn_signup)
        btnSignUp.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        //do nothing
    }
}

