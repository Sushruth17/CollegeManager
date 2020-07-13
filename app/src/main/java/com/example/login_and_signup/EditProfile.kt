package com.example.login_and_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_signup.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val buttonSignup = findViewById<Button>(R.id.btnProfileEdit)
        buttonSignup.setOnClickListener {

            Log.i("edit", "--------clicked button-------------- ")
            val name = editProfileName.getText().toString()
            Log.i("edit", "--------name-------------- $name")
            val username = editProfileUsername.getText().toString()
            Log.i("edit", "--------username-------------- $username")
            val emailId = editProfileEmailId.getText().toString()
            Log.i("edit", "--------emailId-------------- $emailId")
            val password: String = editProfilePassword.getText().toString()
            Log.i("edit", "--------password-------------- $password")
            val confirmPassword: String = editProfileConfirmPassword.getText().toString()
            Log.i("edit", "--------confirmPassword-------------- $confirmPassword")


            if (StringUtils.checkRegex(emailId, StringUtils.EMAIL_PATTERN)) {
                Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show()
            }

            if (password != confirmPassword){
                Toast.makeText(getApplicationContext(),"Paswword does not match", Toast.LENGTH_SHORT).show()
            }


            val jsonEditProfileObj = JsonObject()
            jsonEditProfileObj.addProperty("name", name)
            jsonEditProfileObj.addProperty("username", username)
            jsonEditProfileObj.addProperty("email id", emailId)
            jsonEditProfileObj.addProperty("password", password)

            ApiStudent()
                .addRetroFit()
                ?.editProfile(jsonEditProfileObj)
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