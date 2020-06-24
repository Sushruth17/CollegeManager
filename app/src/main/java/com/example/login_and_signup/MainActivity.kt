package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_and_signup.utils.ApiStudent
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("add","--------inside activity main-------------- " )

        val button = findViewById<Button>(R.id.btn_signup)
        button.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
        val buttonSignin = findViewById<Button>(R.id.btn_signin)
        buttonSignin.setOnClickListener{
            Log.i("add","--------clicked button-------------- " )
            val username : String = sign_in_username.getText().toString()
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
//                            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            if (msg == "Successfully signed in")
                            {
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, Home::class.java)
                                startActivity(intent)
                            }
                            else if (msg == "Password wrong"){
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            }
                            else {
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })

/*            val intent = Intent(this, Home::class.java)
            startActivity(intent)*/
        }
    }

    override fun onBackPressed() {
        //do nothing
    }
}

