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
import kotlinx.android.synthetic.main.activity_edit_student.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)


        val nameRecevied = intent.getStringExtra("Name")
        Log.i("","----name-----"+nameRecevied)

        edit_name.setText(nameRecevied)


        val buttonAdd = findViewById<Button>(R.id.btn_edit)
        buttonAdd.setOnClickListener {

//            finish()
//            val intentRecevied = intent



            val name_edited: String = edit_name.text.toString()
            Log.i("add", "--------ADDNAME-------------- " + name_edited)
            val address_edited: String = edit_address.text.toString()
            Log.i("add", "--------ADDADDRES-------------- " + address_edited)
            val age_edited: String = edit_age.text.toString()
            Log.i("add", "--------ADDAGE-------------- " + age_edited)
            val parentname_edited: String = edit_parent_name.text.toString()
            Log.i("add", "--------ADDPARENTNAME-------------- " + parentname_edited)


            val jsonObj = JsonObject()
            jsonObj.addProperty("name", name_edited)
            jsonObj.addProperty("address", address_edited)
            jsonObj.addProperty("age", age_edited)
            jsonObj.addProperty("parentname", parentname_edited)
            //  POST demo
            ApiAddStudent()
                .addRetroFit()
                ?.editStudent(jsonObj)
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
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}



