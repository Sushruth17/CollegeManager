package com.example.login_and_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_signup.*

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


            val jsonObj = JsonObject()
            jsonObj.addProperty("name", name)
            jsonObj.addProperty("username", username)
            jsonObj.addProperty("email id", emailId)
            jsonObj.addProperty("password", password)
            jsonObj.addProperty("confirm password", confirmPassword)
        }
    }
}