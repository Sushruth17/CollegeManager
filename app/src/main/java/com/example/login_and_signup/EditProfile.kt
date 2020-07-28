package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.login_and_signup.utils.ApiStudent
import com.example.login_and_signup.utils.SharedPreference
import com.example.login_and_signup.utils.StringUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_signup.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile : AppCompatActivity(){
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        edit_profile_toolbar.setNavigationIcon(R.drawable.ic_back)
        edit_profile_toolbar.setNavigationOnClickListener(View.OnClickListener { // Your code
            finish()
        })


        val userName =
            SharedPreference.getValueString(this, StringUtils.USER_NAME) ?: StringUtils.NOT_VALID
        Log.i("edit", "--------userName-------------- " + userName)
        val userUsername = SharedPreference.getValueString(this, StringUtils.USER_USERNAME)
            ?: StringUtils.NOT_VALID
        Log.i("edit", "--------userUsername-------------- $userUsername")
        val userPhoneNumber = SharedPreference.getValueString(this, StringUtils.USER_PHONE_NUMBER)
            ?: StringUtils.NOT_VALID
        Log.i("type", "---userPhoneNumber--- " + userPhoneNumber)

        editProfileName.setText(userName)
        editProfileUsername.setText(userUsername)
        editProfilePhone.setText(userPhoneNumber)

        val buttonSignup = findViewById<Button>(R.id.btnProfileEdit)
        buttonSignup.setOnClickListener {


            Log.i("edit", "--------clicked button-------------- ")
            val name = editProfileName.getText().toString()
            Log.i("edit", "--------name-------------- $name")
            val username = editProfileUsername.getText().toString()
            Log.i("edit", "--------username-------------- $username")
            val phoneNumber = editProfilePhone.text.toString()
            Log.i("edit", "--------phoneNumber-------------- $phoneNumber")
            val password: String = editProfilePassword.getText().toString()
            Log.i("edit", "--------password-------------- $password")
            val confirmPassword: String = editProfileConfirmPassword.getText().toString()
            Log.i("edit", "--------confirmPassword-------------- $confirmPassword")

            val userEmailId = SharedPreference.getValueString(this, StringUtils.USER_EMAIL)
                ?: StringUtils.NOT_VALID
            Log.i("edit", "---userEmailId--- $userEmailId")


/*
            if (StringUtils.checkRegex(emailId, StringUtils.EMAIL_PATTERN)) {
                Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show()
            }
*/

            if (name.isEmpty() or username.isEmpty() or phoneNumber.isEmpty() or password.isEmpty() or confirmPassword.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill all the fields", Toast.LENGTH_SHORT)
                    .show()
            } else {

                if (phoneNumber.length != 10) {
                    Toast.makeText(applicationContext, "Invalid phone number", Toast.LENGTH_SHORT)
                        .show()
                }
                else {

                    if (password != confirmPassword) {
                        Toast.makeText(
                            applicationContext,
                            "Paswword does not match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else {


                        val jsonEditProfileObj = JsonObject()
                        jsonEditProfileObj.addProperty("name", name)
                        jsonEditProfileObj.addProperty("username", username)
                        jsonEditProfileObj.addProperty("phoneNumber", phoneNumber)
                        jsonEditProfileObj.addProperty("password", password)
                        jsonEditProfileObj.addProperty("userEmailId", userEmailId)

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
                                        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT)
                                            .show()
                                        val intent = Intent(context, MainActivity::class.java)
                                        startActivity(intent)
                                    }
                                }
                            })
                    }
                }
            }
        }
    }
}